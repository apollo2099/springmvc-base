package com.base.cache.redis.exception;

import javax.xml.rpc.ServiceException;

public class RedisServiceException extends ServiceException {
	private static final long serialVersionUID = 5261992825559650924L;

	public RedisServiceException() {
	}

	public RedisServiceException(ServiceErrorCode errorCode) {
		super(errorCode);
	}

	public RedisServiceException(String message, Throwable cause, ServiceErrorCode errorCode) {
		super(message, cause, errorCode);
	}

	public RedisServiceException(String message, ServiceErrorCode errorCode) {
		super(message, errorCode);
	}

	public RedisServiceException(Throwable cause, ServiceErrorCode errorCode) {
		super(cause, errorCode);
	}
}
