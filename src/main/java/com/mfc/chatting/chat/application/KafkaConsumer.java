package com.mfc.chatting.chat.application;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.chatting.chat.domain.ChatRoom;
import com.mfc.chatting.chat.domain.Member;
import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.kafka.CreateChatRoomDto;
import com.mfc.chatting.chat.infrastructure.ChatReactiveRepository;
import com.mfc.chatting.chat.infrastructure.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KafkaConsumer {
	private final ChatRoomRepository chatRoomRepository;
	private final ChatReactiveRepository chatReactiveRepository;

	@KafkaListener(topics = "create-chatroom", containerFactory = "createChatRoomListener")
	public void createChatRoom(CreateChatRoomDto dto) {
		ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder()
				.requestId(dto.getRequestId())
				.members(dto.getMembers().stream()
						.map(Member::new)
						.toList())
				.build());

		chatReactiveRepository.save(Message.builder()
				.type("system")
				.msg("Chat Room Open")
				.sender("system")
				.roomId(chatRoom.getId())
				.build()).subscribe();
	}
}
