package com.mfc.chatting.chat.application;

import java.time.Instant;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfc.chatting.chat.domain.Card;
import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.req.ChatReqDto;
import com.mfc.chatting.chat.infrastructure.ChatRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	private final ChatRepository chatRepository;
	private final ObjectMapper objectMapper;

	@Override
	public Flux<Message> getChat(String sender, String receiver) {
		return chatRepository.findChatBySenderAndReceiver(sender, receiver);
	}

	@Override
	public Flux<Message> getChatByStream(Long roomId) {
		return chatRepository.findChatByRoomId(roomId);
	}

	@Override
	public Mono<Message> sendChat(ChatReqDto dto, String uuid) {
		String type = dto.getType();
		Card card = null;
		String msg = null;

		if (type.equals("card")) {
			card = objectMapper.convertValue(dto.getMsg(), Card.class);
		} else {
			msg = (String) dto.getMsg();
		}

		return chatRepository.save(Message.builder()
			.type(type)
			.msg(card == null ? msg : card)
			.sender(uuid)
			.roomId(dto.getRoomId())
			.build());
	}

	@Override
	public Flux<Message> getChatByPage(Long roomId, Instant createdAt, Pageable page) {
		return chatRepository.findByRoomIdAndCreatedAtBefore(roomId, createdAt, page);
	}
}
