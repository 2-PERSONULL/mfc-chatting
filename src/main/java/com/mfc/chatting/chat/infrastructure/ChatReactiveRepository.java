package com.mfc.chatting.chat.infrastructure;

import java.time.Instant;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import com.mfc.chatting.chat.domain.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatReactiveRepository extends ReactiveMongoRepository<Message, String> {
	@Tailable
	@Query("{ 'roomId' : ?0, 'createdAt': { $gt: ?1 }}")
	Flux<Message> findChatByRoomId(String roomId, Instant createdAt);

	@Query("{ 'roomId' : ?0, 'createdAt': { $lte: ?1 } }")
	Flux<Message> findByRoomIdAndCreatedAtBefore(String roomId, Instant createdAt,Pageable page);

	@Query("{ 'roomId': ?0, '_id': ?1 }")
	Mono<Message> findByRoomIdAndId(String roomId, String msgId);
}
