package com.mfc.chatting.chat.application;

import static com.mfc.chatting.common.response.BaseResponseStatus.*;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.chatting.chat.domain.ChatRoom;
import com.mfc.chatting.chat.domain.Member;
import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.resp.ChatRoomRespDto;
import com.mfc.chatting.chat.infrastructure.ChatRepository;
import com.mfc.chatting.chat.infrastructure.ChatRoomRepository;
import com.mfc.chatting.chat.vo.req.ChatRoomVo;
import com.mfc.chatting.common.exception.BaseException;
import com.mfc.chatting.common.response.BaseResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService{
	private final ChatRoomRepository chatRoomRepository;

	@Override
	public void createChatRoom(ChatRoomVo chatRoomVo) {
		ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder()
				.members(chatRoomVo.getMembers())
				.requestId(chatRoomVo.getRequestId())
				.createdAt(Instant.now())
				.build());
	}

	@Override
	public ChatRoomRespDto getRoomDetail(String requestId, String partnerId, String loginId) {
		ChatRoom chatRoom = chatRoomRepository.findByRequestIdAndMemberId(requestId, partnerId)
				.orElseThrow(() -> new BaseException(CHATROOM_NOT_FOUND));

		return ChatRoomRespDto.builder()
				.roomId(chatRoom.getId())
				.unreadCount(chatRoom.getMembers().stream()
						.filter(member -> member.getMemberId().equals(loginId))
						.map(Member::getUnreadCount)
						.findFirst()
						.orElse(0))
				.build();
	}

	@Override
	@Transactional(readOnly = true)
	public void enterChatRoom(String roomId, String uuid) {
		ChatRoom chatRoom = chatRoomRepository.findByIdAndMemberId(roomId, uuid)
				.orElseThrow(() -> new BaseException(CHATROOM_NOT_FOUND));

		List<Member> members = chatRoom.getMembers().stream()
				.map(member -> {
					if(member.getMemberId().equals(uuid)) {
						member = Member.builder()
								.memberId(uuid)
								.unreadCount(0)
								.exitTime(member.getExitTime())
								.enterTime(Instant.now())
								.build();
					}
					return member;
				})
				.toList();

		updateChatRoom(chatRoom, members);
	}

	@Override
	public void exitChatRoom(String roomId, String uuid) {
		ChatRoom chatRoom = chatRoomRepository.findByIdAndMemberId(roomId, uuid)
				.orElseThrow(() -> new BaseException(CHATROOM_NOT_FOUND));

		List<Member> members = chatRoom.getMembers().stream()
				.map(member -> {
					if(member.getMemberId().equals(uuid)) {
						member = Member.builder()
								.memberId(uuid)
								.unreadCount(0)
								.exitTime(Instant.now())
								.enterTime(member.getEnterTime())
								.build();
					}
					return member;
				})
				.toList();

		updateChatRoom(chatRoom, members);
	}

	private void updateChatRoom(ChatRoom chatRoom, List<Member> members) {
		chatRoomRepository.save(ChatRoom.builder()
				.id(chatRoom.getId())
				.members(members)
				.requestId(chatRoom.getRequestId())
				.createdAt(chatRoom.getCreatedAt())
				.build()
		);
	}
}
