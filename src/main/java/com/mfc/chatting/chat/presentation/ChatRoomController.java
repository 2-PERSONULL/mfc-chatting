package com.mfc.chatting.chat.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.chatting.chat.application.ChatRoomService;
import com.mfc.chatting.chat.vo.req.ChatRoomVo;
import com.mfc.chatting.chat.vo.resp.ChatRoomRespVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {
	private final ChatRoomService chatRoomService;
	private final ModelMapper modelMapper;

	@PostMapping
	public void createChatRoom(@RequestBody ChatRoomVo chatRoomVo) {
		chatRoomService.createChatRoom(chatRoomVo);
	}

	@GetMapping("/{requestId}")
	public ChatRoomRespVo getChatRoomDetail(
			@PathVariable String requestId,
			@RequestHeader("partnerId") String partnerId,
			@RequestHeader("UUID") String loginId) {
		return modelMapper.map(
				chatRoomService.getRoomDetail(requestId, partnerId, loginId), ChatRoomRespVo.class);
	}
}
