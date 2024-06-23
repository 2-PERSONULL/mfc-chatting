package com.mfc.chatting.chat.infrastructure;

import java.time.Instant;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mfc.chatting.chat.domain.Message;

public interface ChatRepository extends MongoRepository<Message, String> {
	@Query("{ 'roomId' : ?0, 'createdAt': { $lte: ?1 } }")
	Slice<Message> findByRoomIdAndCreatedAtBefore(String roomId, Instant createdAt, Pageable page);
}
