package com.mfc.chatting.chat.vo.req;

import lombok.Getter;
import lombok.ToString;

@Getter
public class ChatReqVo {
	private String msg;
	private String sender;
	// private String receiver;
	private Long roomId;
}
