package com.mfc.chatting.chat.vo.resp;

import java.util.List;

import com.mfc.chatting.chat.domain.Message;

import lombok.Getter;

@Getter
public class ChatPageRespVo {
	private List<Message> chats;
	private boolean last;
}
