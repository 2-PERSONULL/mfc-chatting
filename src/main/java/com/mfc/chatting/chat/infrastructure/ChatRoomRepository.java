package com.mfc.chatting.chat.infrastructure;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mfc.chatting.chat.domain.ChatRoom;

public interface ChatRoomRepository extends ReactiveMongoRepository<ChatRoom, String> {
}
