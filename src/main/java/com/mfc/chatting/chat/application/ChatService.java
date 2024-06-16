package com.mfc.chatting.chat.application;

import java.time.Instant;

import org.springframework.data.domain.Pageable;

import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.req.ChatReqDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatService {
	Flux<Message> getChat(String sender, String receiver);

	Flux<Message> getChatByStream(Long roomId);
	Flux<Message> getChatByPage(Long roomId, Instant createdAt, Pageable page);

	Mono<Message> sendChat(ChatReqDto dto, String uuid);
}
