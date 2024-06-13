package com.mfc.chatting.chat.domain;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Getter
@Document(collection = "message")
public class Message {
	@Id
	private String id;
	private Long roomId;
	private String type;
	private Object msg;
	private String sender;
	private Instant createdAt;

	@Builder
	public Message(Object msg,String type, String sender, Long roomId) {
		this.msg = msg;
		this.type = type;
		this.sender = sender;
		this.roomId = roomId;
		this.createdAt = Instant.now();
	}
}
