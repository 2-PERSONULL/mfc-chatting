package com.mfc.chatting.chat.application;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.chatting.chat.domain.ChatRoom;
import com.mfc.chatting.chat.domain.Member;
import com.mfc.chatting.chat.dto.resp.ChatRoomRespDto;
import com.mfc.chatting.chat.infrastructure.ChatRoomRepository;
import com.mfc.chatting.chat.vo.req.ChatRoomVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService{
	private final ChatRoomRepository chatRoomRepository;

	@Override
	public void createChatRoom(ChatRoomVo chatRoomVo) {
		chatRoomRepository.save(ChatRoom.builder()
				.members(chatRoomVo.getMembers())
				.requestId(chatRoomVo.getRequestId())
				.createdAt(Instant.now())
				.build());
	}

	@Override
	public ChatRoomRespDto getRoomDetail(String requestId, String partnerId, String loginId) {
		ChatRoom chatRoom = chatRoomRepository.findByRequestIdAndMemberId(requestId, partnerId);

		return ChatRoomRespDto.builder()
				.roomId(chatRoom.getId())
				.unreadCount(chatRoom.getMembers().stream()
						.filter(member -> member.getMemberId().equals(loginId))
						.map(Member::getUnreadCount)
						.findFirst()
						.orElse(0))
				.build();
	}
}
