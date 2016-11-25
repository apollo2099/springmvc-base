package com.base.cache.redis;

/**
 * 内部接口,不要使用
 *
 * @author tingfeng
 * @since 1.0.0
 */
public interface RedisFactory<T> {

    T getResource();

    void returnResource(T resource);

    String getHost();

    int getPort();

    JedisPool getPool();

}
