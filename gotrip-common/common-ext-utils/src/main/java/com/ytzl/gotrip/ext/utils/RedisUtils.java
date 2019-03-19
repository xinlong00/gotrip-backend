package com.ytzl.gotrip.ext.utils;

import com.ytzl.gotrip.utils.common.Constants;
import com.ytzl.gotrip.utils.common.EmptyUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * RedisAPI
 *
 * @author sam
 */
@Component
public class RedisUtils {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * set key and value to redis
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
        return true;
    }

    /**
     * set key and value to redis
     *
     * @param key
     * @param seconds 有效期
     * @param value
     * @return
     */
    public boolean set(final String key, final String value, final long seconds) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                byte keyByte[] = stringRedisSerializer.serialize(key);
                //value可以随意设置
                byte valueByte[] = stringRedisSerializer.serialize(value);
                redisConnection.set(keyByte,
                        valueByte,
                        Expiration.seconds(seconds), RedisStringCommands.SetOption.UPSERT);
                return true;
            }
        });
    }


    public boolean expire(final String key, final long seconds) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                byte keyByte[] = stringRedisSerializer.serialize(key);
                redisConnection.expire(keyByte, seconds);
                return true;
            }
        });
    }


    /**
     * 判断某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean exist(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        Object value = vo.get(key);
        return EmptyUtils.isEmpty(value) ? false : true;
    }

    public Object get(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    public void delete(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //使用redis的setnx命令实现分布式锁

    /***
     * 持有锁
     * @param key 商品对应的唯一的用于分布式锁的key值
     * @return
     */
    public boolean lock(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                byte keyByte[] = stringRedisSerializer.serialize(key);
                //value可以随意设置
                byte valueByte[] = stringRedisSerializer.serialize("lock");
                boolean flag = redisConnection.setNX(keyByte, valueByte);
                //防止死锁，设置最大处理的超时时间
                if (flag) {
                    redisConnection.expire(keyByte, Constants.RedisExpire.DEFAULT_EXPIRE);
                }
                return flag;
            }
        });
    }

    /***
     * 释放锁
     * @param key
     * @return
     */
    public void unLock(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());//设置序列化
        redisTemplate.delete(key);
    }

    public boolean validate(String token) {
        return exist(token);
    }


    public long ttl(final String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                byte keyByte[] = stringRedisSerializer.serialize(key);
                return redisConnection.ttl(keyByte);
            }
        });
    }
}
