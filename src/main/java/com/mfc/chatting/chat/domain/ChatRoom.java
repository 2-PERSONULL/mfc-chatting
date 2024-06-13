package com.mfc.chatting.chat.domain;

import java.lang.reflect.Member;
import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "chatroom")
public class ChatRoom {
	@Id
	private Long id;
	private Long requestId;
	private List<Member> members;
	private Instant createAt;
	private Instant updateAt;
}
