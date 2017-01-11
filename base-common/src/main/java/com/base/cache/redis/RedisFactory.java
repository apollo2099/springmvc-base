package com.base.cache.redis;

import com.base.cache.redis.exception.RedisServiceException;
import redis.clients.jedis.JedisPool;

/**
 * 内部接口,不要使用
 *
 * @author tingfeng
 * @since 1.0.0
 */
public interface RedisFactory<T> {

    T getResource() throws RedisServiceException;

    void returnResource(T resource);

    String getHost();

    int getPort();

    JedisPool getPool();

}
