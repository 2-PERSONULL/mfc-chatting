package com.mfc.chatting.chat.application;

import org.springframework.stereotype.Service;

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

	@Override
	public Flux<Message> getChat(String sender, String receiver) {
		return chatRepository.findChatBySenderAndReceiver(sender, receiver);
	}

	@Override
	public Flux<Message> getChatByRoomId(String roomId) {
		return chatRepository.findChatByRoomId(roomId);
	}

	@Override
	public Mono<Message> sendChat(ChatReqDto dto) {
		return chatRepository.save(Message.builder()
			.msg(dto.getMsg())
			.sender(dto.getSender())
			.roomId(dto.getRoomId())
			// .receiver(chatVo.getReceiver())
			// .createdAt(chatVo)
			.build());
	}
}
