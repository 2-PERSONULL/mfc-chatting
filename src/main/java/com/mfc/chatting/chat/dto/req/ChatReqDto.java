package com.mfc.chatting.chat.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatReqDto {
	private String roomId;
	private String type;
	private Object msg;
}
