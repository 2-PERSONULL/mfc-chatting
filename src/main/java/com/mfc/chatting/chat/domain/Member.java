package com.mfc.chatting.chat.domain;

import java.time.Instant;

import lombok.Getter;

@Getter
public class Member {
	private String memberId;
	private int unreadCount;
	private Instant enterTime;
	private Instant exitTime;
}
