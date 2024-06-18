package com.mfc.chatting.chat.infrastructure;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mfc.chatting.chat.domain.ChatRoom;
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
	@Query("{ 'requestId': ?0, 'members.memberId': ?1 }")
	Optional<ChatRoom> findByRequestIdAndMemberId(String requestId, String memberId);

	@Query("{ '_id': ?0, 'members.memberId': ?1 }")
	Optional<ChatRoom> findByIdAndMemberId(String roomId, String memberId);
}
