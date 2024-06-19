package com.mfc.chatting.chat.domain;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
	private String memberId;
	private int unreadCount;
	private Instant enterTime;
	private Instant exitTime;

	public Member(String memberId) {
		this.memberId = memberId;
		this.unreadCount = 0;
		this.enterTime = Instant.now();
		this.exitTime = Instant.now();
	}

	public boolean isEnterTimeBeforeExitTime() {
		return enterTime.isBefore(exitTime) || enterTime.equals(exitTime);
	}
}
