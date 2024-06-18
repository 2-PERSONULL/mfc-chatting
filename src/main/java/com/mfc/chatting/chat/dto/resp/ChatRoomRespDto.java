package com.mfc.chatting.chat.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomRespDto {
	private String roomId;
	private Integer unreadCount;
}
