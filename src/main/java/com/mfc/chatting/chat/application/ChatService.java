package com.mfc.chatting.chat.application;

import java.time.Instant;

import org.springframework.data.domain.Pageable;

import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.req.ChatReqDto;
import com.mfc.chatting.chat.dto.resp.ChatPageRespDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatService {
	Flux<Message> getChatByStream(String roomId, String uuid, Instant now);
	ChatPageRespDto getChatByPage(String roomId, String uuid, Pageable page);

	Mono<Message> sendChat(ChatReqDto dto, String uuid);
}
