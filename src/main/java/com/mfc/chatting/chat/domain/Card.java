package com.mfc.chatting.chat.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class Card {
	private String title;
	private String description;
	private List<Action> actions;
	private List<Detail> details;
}
