package com.mfc.chatting.chat.application;

import static com.mfc.chatting.common.response.BaseResponseStatus.*;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfc.chatting.chat.domain.Card;
import com.mfc.chatting.chat.domain.ChatRoom;
import com.mfc.chatting.chat.domain.Member;
import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.req.ChatReqDto;
import com.mfc.chatting.chat.infrastructure.ChatRepository;
import com.mfc.chatting.chat.infrastructure.ChatRoomRepository;
import com.mfc.chatting.common.exception.BaseException;
import com.mfc.chatting.common.response.BaseResponseStatus;

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
	private final ChatRoomRepository chatRoomRepository;

	@Override
	public Flux<Message> getChat(String sender, String receiver) {
		return chatRepository.findChatBySenderAndReceiver(sender, receiver);
	}

	@Override
	public Flux<Message> getChatByStream(String roomId) {
		return chatRepository.findChatByRoomId(roomId);
	}

	@Override
	public Mono<Message> sendChat(ChatReqDto dto, String uuid) {
		ChatRoom chatRoom = chatRoomRepository.findById(dto.getRoomId())
				.orElseThrow(() -> new BaseException(CHATROOM_NOT_FOUND));

		String type = dto.getType();
		Card card = null;
		String msg = null;

		if (type.equals("card")) {
			card = objectMapper.convertValue(dto.getMsg(), Card.class);
		} else {
			msg = (String) dto.getMsg();
		}

		List<Member> members = chatRoom.getMembers().stream()
				.map(member -> {
					if(!member.getMemberId().equals(uuid) && member.isEnterTimeBeforeExitTime()) {
						member = Member.builder()
								.memberId(member.getMemberId())
								.unreadCount(member.getUnreadCount() + 1)
								.exitTime(member.getExitTime())
								.enterTime(member.getEnterTime())
								.build();
					}
					return member;
				})
				.toList();

		chatRoomRepository.save(ChatRoom.builder()
				.id(chatRoom.getId())
				.requestId(chatRoom.getRequestId())
				.members(members)
				.createdAt(chatRoom.getCreatedAt())
				.build());

		return chatRepository.save(Message.builder()
			.type(type)
			.msg(card == null ? msg : card)
			.sender(uuid)
			.roomId(dto.getRoomId())
			.build());
	}

	@Override
	public Flux<Message> getChatByPage(String roomId, Instant createdAt, Pageable page) {
		return chatRepository.findByRoomIdAndCreatedAtBefore(roomId, createdAt, page);
	}
}
