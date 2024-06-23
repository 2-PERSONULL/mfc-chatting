package com.mfc.chatting.chat.dto.resp;

import java.util.List;

import com.mfc.chatting.chat.domain.Message;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatPageRespDto {
	private List<Message> chats;
	private boolean last;
}
