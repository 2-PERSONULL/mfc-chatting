package com.mfc.chatting.chat.vo.req;

import lombok.Getter;

@Getter
public class ChatReqVo {
	private Long roomId;
	private String type;
	private Object msg;
}
