package com.mfc.chatting.chat.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Getter
@Document(collection = "message")
public class Message {
	@Id
	private String id;
	private String roomId;
	private String msg;
	private String sender;
	// private String receiver;		// 카카오톡 답글, 멘션 같은 부분에 사용될 수 있음
	private LocalDateTime createdAt;

	@Builder
	public Message(String msg, String sender, String roomId) {
		this.msg = msg;
		this.sender = sender;
		// this.receiver = receiver;
		this.roomId = roomId;
		this.createdAt = LocalDateTime.now();
	}
}
