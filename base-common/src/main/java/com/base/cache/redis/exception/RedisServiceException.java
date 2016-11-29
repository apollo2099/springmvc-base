package com.base.cache.redis.exception;

public class RedisServiceException extends Exception{
	private static final long serialVersionUID = 5261992825559650924L;

	public RedisServiceException(String msg,Integer code) {
		 super(msg);  
	}
}
