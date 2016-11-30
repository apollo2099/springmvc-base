package com.base.cache.redis;

import java.util.concurrent.locks.ReentrantLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.base.cache.redis.exception.RedisServiceException;
import com.base.cache.redis.utils.RedisConfig;
import com.base.cache.redis.utils.RedisConstant;

/**
 * Created by Administrator on 2016/11/25.
 */
public class DefaultJedisFactory implements RedisFactory<Jedis> {

    private JedisPool pool;

    private static ReentrantLock lockPool = new ReentrantLock();

    private static ReentrantLock lockJedis = new ReentrantLock();

    private static class JedisFactoryHolder {
        private static final DefaultJedisFactory INSTANCE = new DefaultJedisFactory();
    }

    private DefaultJedisFactory() {
    }

    public static final DefaultJedisFactory getInstance() {
        return JedisFactoryHolder.INSTANCE;
    }

    private void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(RedisConfig.getConfig(RedisConstant.REDIS_MAX_ACTIVE, RedisConstant.MAX_ACTIVE));
        config.setMaxIdle(RedisConfig.getConfig(RedisConstant.REDIS_MAXIDLE, RedisConstant.MAX_IDLE));
        config.setMaxWaitMillis(RedisConfig.getConfig(RedisConstant.REDIS_MAX_WAIT, RedisConstant.MAX_WAIT_MILLIS));
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);

        pool = new JedisPool(config,
                RedisConfig.getConfig(RedisConstant.REDIS_HOST),
                RedisConfig.getConfig(RedisConstant.REDIS_PORT, RedisConstant.DEFAULT_PORT),
                RedisConfig.getConfig(RedisConstant.REDIS_TIMEOUT, RedisConstant.TIMEOUT),
                RedisConfig.getConfig(RedisConstant.REDIS_PASSWORD));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                pool.destroy();
            }
        });
    }

    @Override
    public Jedis getResource() {
        if (this.pool == null) {
            lockPool.lock();
            try {
                if (this.pool == null) {
                    this.initPool();
                }
            } finally {
                lockPool.unlock();
            }
        }

        if (this.pool == null) {
            //throw new RedisServiceException("Jedis not initialized.", ServiceErrorCode.REDIS_CLIENT_NOT_INITIALIZED);
        }

        Jedis jedis = pool.getResource();

        return jedis;
    }

    @Override
    public void returnResource(Jedis resource) {
        if (pool != null) {
            pool.returnResourceObject(resource);
        }
    }

    @Override
    public String getHost() {
        return RedisConfig.getConfig(RedisConstant.REDIS_HOST);
    }

    @Override
    public int getPort() {
        return RedisConfig.getConfig(RedisConstant.REDIS_PORT, RedisConstant.DEFAULT_PORT);
    }

    @Override
    public JedisPool getPool() {
        return pool;
    }
}