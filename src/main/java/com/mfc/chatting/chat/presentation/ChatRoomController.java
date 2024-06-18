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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Tag(name = "chatroom", description = "채팅방 서비스 컨트롤러")
public class ChatRoomController {
	private final ChatRoomService chatRoomService;
	private final ModelMapper modelMapper;

	@PostMapping
	@Operation(summary = "채팅방 생성 API", description = "백엔드 테스트용.. 실제로는 카프카로 동작함!")
	public void createChatRoom(@RequestBody ChatRoomVo chatRoomVo) {
		chatRoomService.createChatRoom(chatRoomVo);
	}

	@GetMapping("/{requestId}")
	@Operation(summary = "채팅방 정보 조회 API", description = "채팅방 ID, 유저 별 안읽은 메세지 개수 반환")
	public ChatRoomRespVo getChatRoomDetail(
			@PathVariable String requestId,
			@RequestHeader("partnerId") String partnerId,
			@RequestHeader("UUID") String loginId) {
		return modelMapper.map(
				chatRoomService.getRoomDetail(requestId, partnerId, loginId), ChatRoomRespVo.class);
	}
}
