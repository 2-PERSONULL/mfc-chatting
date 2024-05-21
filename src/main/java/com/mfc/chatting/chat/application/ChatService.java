package com.mfc.chatting.chat.application;

import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.req.ChatReqDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatService {
	Flux<Message> getChat(String sender, String receiver);

	Flux<Message> getChatByRoomId(String roomId);

	Mono<Message> sendChat(ChatReqDto dto);
}
