package com.mfc.chatting.chat.presentation;
import org.modelmapper.ModelMapper;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.chatting.chat.application.ChatService;
import com.mfc.chatting.chat.domain.Message;
import com.mfc.chatting.chat.dto.req.ChatReqDto;
import com.mfc.chatting.chat.vo.req.ChatReqVo;


import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {
	private final ChatService chatService;
	private final ModelMapper modelMapper;

	// receiver 없앰 -> 안쓰는 API ( 이거는 귓속말에 사용가능함 )
	// @GetMapping(value="/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	// public Flux<Message> getChat(
	// 	@PathVariable(value ="sender") String sender,
	// 	@PathVariable(value ="receiver") String receiver
	// ){
	// 	return chatService.getChat(sender, receiver)
	// 		.subscribeOn(Schedulers.boundedElastic());
	// }

	@GetMapping(value = "/room/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Message> getChatByRoomId(
		@PathVariable(value ="roomId") String roomId
	){
		return chatService.getChatByRoomId(roomId).subscribeOn(Schedulers.boundedElastic());
	}

	@PostMapping("")
	public Mono<Message> sendChat(@RequestBody ChatReqVo vo){
		return chatService.sendChat(modelMapper.map(vo, ChatReqDto.class));
	}
}
