package com.mfc.chatting.chat.infrastructure;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import com.mfc.chatting.chat.domain.Message;

import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Message, Long> {

	@Tailable
	@Query("{ sender : ?0, receiver : ?1}")
	Flux<Message> findChatBySenderAndReceiver(String sender, String receiver);
	@Tailable
	@Query("{ 'roomId' : ?0}")
	Flux<Message> findChatByRoomId(String roomId);

	// @Tailable
	// Flux<Chat> msgFindBySender(String sender, String receiver);
}
