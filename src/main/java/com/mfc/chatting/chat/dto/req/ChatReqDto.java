package com.mfc.chatting.chat.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatReqDto {
	private String msg;
	private String sender;
	// private String receiver;
	private Long roomId;
}
