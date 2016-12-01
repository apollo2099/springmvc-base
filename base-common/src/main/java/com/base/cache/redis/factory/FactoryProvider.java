package com.base.cache.redis.factory;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface FactoryProvider<I> {
    Class<I> getBaseInterface();

    I getFactoryInstance();
}