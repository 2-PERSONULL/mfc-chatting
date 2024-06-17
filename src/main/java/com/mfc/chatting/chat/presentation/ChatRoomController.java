package com.mfc.chatting.chat.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.chatting.chat.application.ChatRoomService;
import com.mfc.chatting.chat.vo.req.ChatRoomVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {
	private final ChatRoomService chatRoomService;

	@PostMapping
	public void createChatRoom(@RequestBody ChatRoomVo chatRoomVo) {
		chatRoomService.createChatRoom(chatRoomVo);
	}
}
