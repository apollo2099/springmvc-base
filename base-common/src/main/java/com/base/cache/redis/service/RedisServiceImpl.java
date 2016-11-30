package com.base.cache.redis.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import com.base.cache.redis.DefaultJedisFactory;
import com.base.cache.redis.RedisFactory;
import com.base.cache.redis.ServiceErrorCode;
import com.base.cache.redis.exception.RedisServiceException;

/**
 * Redis 接口操作工具类
 */
public class RedisServiceImpl implements RedisService{
    private RedisFactory<Jedis>	redisFactory;

    private String host;

    private int port;

    public RedisServiceImpl() {
        this(DefaultJedisFactory.getInstance());
    }

    public RedisServiceImpl(RedisFactory<Jedis> redisFactory) {
        this.redisFactory = redisFactory;
        //监控增加
        host = redisFactory.getHost();
        port = redisFactory.getPort();
    }

    abstract class Executor<T> {

        Jedis	jedis;

        public Executor() {
            long startTime = System.currentTimeMillis();

            jedis = redisFactory.getResource();

            long endTime = System.currentTimeMillis();
            long duration = endTime-startTime;
            sb.append("getResourceTime="+duration);
        }

        private StringBuffer sb = new StringBuffer();

        /**
         * 回调
         *
         * @return 执行结果
         */
        abstract T action();

        /**
         * 调用{@link #action()}并返回执行结果 它保证在执行{@link #action()}
         * 之后释放数据源returnResource(jedis)
         *
         * @return 执行结果
         */
        public T execute(String operation,String... strings) {
            T result = null;
            boolean broken = false;
            try {
                long startTime = System.currentTimeMillis();

                result = action();
                //增加监控
                long endTime = System.currentTimeMillis();
                long duration = endTime-startTime;
                String content = ", operation="+operation+", duration="+duration+", parameters="+Arrays.asList(strings).toString()+", redis.host="+host+" and redis.port="+port;
                sb.append(content);
            } catch (Throwable e) {
                broken = handleException(e);
                //throw new RedisServiceException("Redis execute exception, " + e.getMessage(), ServiceErrorCode.REDIS_EXECUTE_FAIL_ERROR);
            } finally {
                if (jedis != null) {
                    redisFactory.returnResource(jedis);
                }
            }
            return result;
        }

        public String getSb(){
            return sb.toString();
        }

        private boolean handleException(Throwable e) {
            if (e instanceof JedisDataException) {
                if ((e.getMessage() == null) || (e.getMessage().indexOf("READONLY") == -1)) {
                    // dataException, isBroken=false
                    return false;
                }
            }
            return true;
        }
    }



    @Override
    public void set(final byte[] key, final byte[] value, final int seconds) {
        long startTime =System.currentTimeMillis();
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return (seconds > 0)? jedis.setex(key, seconds, value): jedis.set(key, value);
            }
        };
        executor.execute("redis.set(byte,byte) and key.length="+key.length+" and value.length="+value.length,"");
        addAnnotation(startTime,executor.getSb());
    }

    @Override
    public void set(final String key, final String value, final int seconds) {
        long startTime = System.currentTimeMillis();
        Executor<String> executor =new Executor<String>() {
            @Override
            String action() {
                return (seconds > 0)? jedis.setex(key, seconds, value): jedis.set(key, value);
            }
        };
        executor.execute("redis.set",key,value,String.valueOf(seconds));
        addAnnotation(startTime,executor.getSb());
    }

    public Long setnx(final String key, final String value) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.setnx(key, value);
            }
        };
        Long result = executor.execute("redis.setnx",key,value);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long expire(final String key, final int seconds) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.expire(key, seconds);
            }
        };
        long result = executor.execute("redis.expire",key,String.valueOf(seconds));
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public byte[] get(final byte[] key) {
        long startTime = System.currentTimeMillis();
        Executor<byte[]> executor = new Executor<byte[]>() {
            @Override
            byte[] action() {
                return jedis.get(key);
            }
        };
        byte[] result = executor.execute("redis.get(byte) and key.length="+key.length,"");
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public String get(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.get(key);
            }
        };
        String result = executor.execute("redis.get",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public void remove(final byte[] key) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.del(key);
            }
        };
        executor.execute("redis.remove(byte) and key.length="+key.length,"");
        addAnnotation(startTime,executor.getSb());
    }

    @Override
    public void remove(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.del(key);
            }
        };
        executor.execute("redis.del",key);
        addAnnotation(startTime,executor.getSb());
    }

    @Override
    public boolean exists(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<Boolean> executor = new Executor<Boolean>() {
            @Override
            Boolean action() {
                return jedis.exists(key);
            }
        };
        boolean result = executor.execute("redis.exists",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public List<String> mget(final String... keys) {
        long startTime = System.currentTimeMillis();
        Executor<List<String>> executor = new Executor<List<String>>() {
            @Override
            List<String> action() {
                return jedis.mget(keys);
            }
        };
        List<String> result = executor.execute("redis.mget",Arrays.asList(keys).toString());
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public String mset(final String... keysvalues) {
        long startTime = System.currentTimeMillis();
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.mset(keysvalues);
            }
        };
        String result = executor.execute("redis.mset", Arrays.asList(keysvalues).toString());
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long rpush(final String key, final String... strings) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.rpush(key, strings);
            }
        };
        long result = executor.execute("redis.rpush",key,Arrays.asList(strings).toString());
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long lpush(final byte[] key, final byte[]... strings) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.lpush(key, strings);
            }
        };
        long result = executor.execute("redis.lpush(byte)","");
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long lpush(final String key, final String... strings) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.lpush(key, strings);
            }
        };
        long result = executor.execute("redis.lpush",key,Arrays.asList(strings).toString());
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public String rpop(final String key) {
        long startTime =System.currentTimeMillis();
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.rpop(key);
            }
        };
        String result = executor.execute("redis.rpop",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public String lpop(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.lpop(key);
            }
        };
        String result = executor.execute("redis.lpop",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public List<String> lrange(final String key, final long start, final long end) {
        long startTime = System.currentTimeMillis();
        Executor<List<String>> executor = new Executor<List<String>>() {
            @Override
            List<String> action() {
                return jedis.lrange(key, start, end);
            }
        };
        List<String> result = executor.execute("redis.lrange",key,String.valueOf(start),String.valueOf(end));
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public String ltrim(final String key, final long start, final long end) {
        long startTime = System.currentTimeMillis();
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.ltrim(key, start, end);
            }
        };
        String result = executor.execute("redis.ltrim",key,String.valueOf(start),String.valueOf(end));
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long lrem(final String key, final long count, final String value) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.lrem(key, count, value);
            }
        };
        Long result = executor.execute("redis.lrem",key,String.valueOf(count),value);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Set<String> hkeys(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<Set<String>> executor = new Executor<Set<String>>() {
            @Override
            Set<String> action() {
                return jedis.hkeys(key);
            }
        };
        Set<String> result = executor.execute("redis.hkeys",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long hset(final String key, final String field, final String value) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.hset(key, field, value);
            }
        };
        long result = executor.execute("redis.hset", key,field,value);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public String hget(final String key, final String field) {
        long startTime = System.currentTimeMillis();
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.hget(key, field);
            }
        };
        String result = executor.execute("redis.hget",key,field);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long zadd(final String key, final double score, final String member) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.zadd(key, score, member);
            }
        };
        long result = executor.execute("redis.zadd",key,String.valueOf(score),member);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long zcard(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.zcard(key);
            }
        };
        long result = executor.execute("redis.zcard",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long zrem(final String key, final String... members) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.zrem(key, members);
            }
        };
        Long result = executor.execute("redis.zrem", key,Arrays.asList(members).toString());
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Double zscore(final String key, final String member) {
        long startTime = System.currentTimeMillis();
        Executor<Double> executor = new Executor<Double>() {
            @Override
            Double action() {
                return jedis.zscore(key, member);
            }
        };
        Double result = executor.execute("redis.zrem", key,member);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Set<String> zrevrange(final String key, final long start, final long end) {
        long startTime = System.currentTimeMillis();
        Executor<Set<String>> executor = new Executor<Set<String>>() {
            @Override
            Set<String> action() {
                return jedis.zrevrange(key, start, end);
            }
        };
        Set<String> result = executor.execute("redis.zrevrange", key,String.valueOf(start),String.valueOf(end));
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long zunionstore(final String key, final String... sets) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.zunionstore(key, sets);
            }
        };
        long result = executor.execute("redis.zunionstore", key,Arrays.asList(sets).toString());
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long decrement(final String key, final long delta) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.decrBy(key, delta);
            }
        };
        Long result = executor.execute("redis.decrBy",key,String.valueOf(delta));
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long increment(final String key, final long delta) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.incrBy(key, delta);
            }
        };
        long result = executor.execute("redis.incrBy",key,String.valueOf(delta));
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long llen(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.llen(key);
            }
        };
        long result = executor.execute("redis.llen",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long ttl(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.ttl(key);
            }
        };
        long result = executor.execute("redis.ttl",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    private void addAnnotation(long startTime,String content){
        long endTime = System.currentTimeMillis();
        long duration = endTime-startTime;
        String tempContent = content+", allDuration="+duration;
        if(duration>=100){
            int numActive = redisFactory.getPool().getNumActive();
            int numIdle = redisFactory.getPool().getNumIdle();
            tempContent = tempContent+", numActive="+numActive+", numIdle="+numIdle;
        }
    }

    @Override
    public Long hincrBy(final String key,final String field,final long value) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.hincrBy(key, field, value);
            }
        };
        long result = executor.execute("redis.hincrBy",key,field);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public boolean hexists(final String key,final String field) {
        long startTime = System.currentTimeMillis();
        Executor<Boolean> executor = new Executor<Boolean>() {
            @Override
            Boolean action() {
                return jedis.hexists(key, field);
            }
        };
        boolean result = executor.execute("redis.hexists",key,field);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long hdel(final String key,final String[] fields) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.hdel(key, fields);
            }
        };
        long result = executor.execute("redis.hdel",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }

    @Override
    public Long incr(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.incr(key);
            }
        };
        long result = executor.execute("redis.incr",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }


    @Override
    public Long decr(final String key) {
        long startTime = System.currentTimeMillis();
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.decr(key);
            }
        };
        long result = executor.execute("redis.decr",key);
        addAnnotation(startTime,executor.getSb());
        return result;
    }
}
