package com.mfc.chatting.chat.presentation;
import org.modelmapper.ModelMapper;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.chatting.chat.application.ChatService;
import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.req.ChatReqDto;
import com.mfc.chatting.chat.vo.req.ChatReqVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
@Tag(name = "chat", description = "채팅 서비스 컨트롤러")
public class ChatController {
	private final ChatService chatService;
	private final ModelMapper modelMapper;

	@GetMapping(value = "/room/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@Operation(summary = "채팅 조회 API", description = "채팅방 번호에 따른 채팅 목록 조회 (동작 안함 ㅎㅎ..)")
	public Flux<Message> getChatByRoomId(
		@PathVariable(value ="roomId") Long roomId){
		return chatService.getChatByRoomId(roomId).subscribeOn(Schedulers.boundedElastic());
	}

	@PostMapping
	@Operation(summary = "채팅 전송 API", description = "type = msg/card/image")
	public Mono<Message> sendChat(@RequestBody ChatReqVo vo,
			@RequestHeader(value = "UUID", defaultValue = "") String uuid){
		return chatService.sendChat(modelMapper.map(vo, ChatReqDto.class),uuid);
	}
}
