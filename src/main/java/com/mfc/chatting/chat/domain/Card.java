package com.mfc.chatting.chat.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class Card {
	private String requestId;
	private String title;
	private String description;
	private String target;
	private String type;
	private List<Action> actions;
	private List<Detail> details;
}
