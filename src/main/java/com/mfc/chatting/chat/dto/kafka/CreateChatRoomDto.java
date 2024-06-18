package com.mfc.chatting.chat.dto.kafka;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRoomDto {
	private String requestId;
	private List<String> members;
}
