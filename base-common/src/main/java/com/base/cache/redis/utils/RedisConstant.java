package com.base.cache.redis.utils;

/**
 * Created by Administrator on 2016/11/25.
 */
public class RedisConstant {
    /**
     * Redis configure
     */
    public static final String	REDIS_HOST			= "redis.host";
    public static final String	REDIS_PORT			= "redis.port";
    public static final String	REDIS_PASSWORD		= "redis.password";
    public static final String	REDIS_MAX_WAIT		= "redis.maxWait";
    public static final String	REDIS_MAX_ACTIVE	= "redis.maxActive";
    public static final String	REDIS_TIMEOUT		= "redis.timeout";
    public static final String	REDIS_TESTONBORROW	= "redis.testOnBorrow";
    public static final String	REDIS_MAXIDLE		= "redis.maxIdle";
    public static final String  REDIS_MIN_IDLE		= "redis.minIdle";

    public static final int     DEFAULT_PORT        = 6379;
    public static final int		MAX_ACTIVE			= 300;
    public static final int		MAX_IDLE			= 100;
    public static final int		MAX_WAIT_MILLIS		= 1000;
    public static final int		TIMEOUT				= 2000;

    public static final int		MIN_IDLE			= 0;
}
