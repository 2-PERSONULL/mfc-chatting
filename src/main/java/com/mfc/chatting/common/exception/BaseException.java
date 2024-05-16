package com.mfc.chatting.common.exception;

import com.mfc.chatting.common.response.BaseResponseStatus;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
	private BaseResponseStatus status;

	public BaseException(BaseResponseStatus status) {
		this.status = status;
	}
}
