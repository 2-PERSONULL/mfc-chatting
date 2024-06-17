package com.mfc.chatting.chat.domain;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Getter
@Document(collection = "chatroom")
public class ChatRoom {
	@Id
	private String id;
	private String requestId;
	private List<Member> members;
	private Instant createdAt;

	@Builder
	public ChatRoom(String id, String requestId, List<Member> members, Instant createdAt) {
		this.id = id;
		this.requestId = requestId;
		this.members = members;
		this.createdAt = createdAt;
	}
}
