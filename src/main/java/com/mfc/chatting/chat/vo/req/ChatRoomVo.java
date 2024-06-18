package com.mfc.chatting.chat.vo.req;

import java.util.List;

import com.mfc.chatting.chat.domain.Member;

import lombok.Getter;

@Getter
public class ChatRoomVo {
    private String requestId;
    private List<Member> members;
}
