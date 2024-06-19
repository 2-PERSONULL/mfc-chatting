package com.mfc.chatting.chat.application;

import com.mfc.chatting.chat.domain.ChatRoom;
import com.mfc.chatting.chat.dto.resp.ChatRoomRespDto;
import com.mfc.chatting.chat.vo.req.ChatRoomVo;

import reactor.core.publisher.Mono;

public interface ChatRoomService {
	void createChatRoom(ChatRoomVo chatRoomVo);
	ChatRoomRespDto getRoomDetail(String requestId, String partnerId, String loginId);
	void enterChatRoom(String roomId, String uuid);
	void exitChatRoom(String roomId, String uuid);
}
