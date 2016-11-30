package com.base.cache.redis.factory;

import com.base.cache.redis.service.RedisService;

/**
 * Created by Administrator on 2016/11/25.
 */
public interface IRedisServiceFactory {

    RedisService getRedisService();

    RedisService getRedisService(String instance);
}
