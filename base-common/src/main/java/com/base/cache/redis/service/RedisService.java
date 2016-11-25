package com.base.cache.redis.service;

/**
 * Redis服务接口
 * @author huixiong
 * @since 1.0.0
 */
public interface RedisService {
    /**
     * 过期的时长，{@code -1}表示永不过期。
     */
    int NEVER_EXPIRE = -1;

    /**
     * 将 value 关联到 key
     * 如果 key 已经存,会覆盖.同时原生存时间会清除。
     *
     * @param key
     * @param value
     * @param seconds 生存时间(秒)
     */
    void set(byte[] key, byte[] value, int seconds);

    /**
     * 将 value 关联到 key
     * 如果 key 已经存,会覆盖.同时原生存时间会清除。
     *
     * @param key
     * @param value
     * @param seconds 生存时间(秒)
     */
    void set(String key, String value, int seconds);

    Long setnx(String key, String value);

    /**
     * @param key
     */
    byte[] get(byte[] key);

    String get(String key);

    /**
     * @param key
     */
    void remove(byte[] key);

    void remove(String key);

    Long expire(String key, int seconds);

    boolean exists(String key);

    List<String> mget(String... keys);

    String mset(String... keysvalues);

    Long rpush(String key, String... strings);

    Long lpush(String key, String... strings);

    Long lpush(byte[] key, byte[]... strings);

    String rpop(String key);

    String lpop(String key);

    Long llen(String key);

    List<String> lrange(String key, long start, long end);

    String ltrim(String key, long start, long end);

    Long lrem(String key, long count, String value);

    Set<String> hkeys(String key);

    Long zadd(String key, double score, String member);

    Set<String> zrevrange(String key, long start, long end);

    Long zcard(String key);

    Long zrem(String key, String... members);

    Double zscore(String key, String member);

    Long zunionstore(String key, String... sets);

    String hget(String key, String field);

    Long hset(String key, String field, String value);

    Long increment(String key, long delta);

    Long decrement(String key, long delta);

    Long ttl(String key);

    Long hincrBy(final String key,final String field,final long value);

    boolean hexists(final String key,final String field);

    Long hdel(String key, String[] fields);

    Long incr(String key);

    Long decr(String key);
}
