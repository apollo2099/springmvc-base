package com.base.cache.redis.factory;

import com.base.cache.redis.service.RedisService;

/**
 * Created by Administrator on 2016/11/25.
 */
public class RedisServiceFactory {

    public static RedisService getRedisService() {
        return getFactory().getRedisService();
    }

    public static RedisService getRedisService(String instance) {
        return getFactory().getRedisService(instance);
    }

    private static IRedisServiceFactory getFactory() {
        return ServiceFactoryFactory.getFactory(IRedisServiceFactory.class);
    }
}
