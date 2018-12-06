package com.kangyonggan.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author kangyonggan
 * @since 8/14/18
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param value
     * @return
     */
    public boolean set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        return true;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    public boolean set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
        return true;
    }

    /**
     * get
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * get
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get(String key, Object defaultValue) {
        Object val = redisTemplate.opsForValue().get(key);

        return val == null ? defaultValue : val;
    }

    /**
     * get and update expire
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Object getAndUpdateExpire(String key, long timeout, TimeUnit unit) {
        Object object = redisTemplate.opsForValue().get(key);
        if (object != null) {
            redisTemplate.expire(key, timeout, unit);
        }
        return object;
    }

    /**
     * multiGet
     *
     * @param keys
     * @return
     */
    public List<Object> multiGet(Set<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * delete
     *
     * @param key
     * @return
     */
    public Object delete(String key) {
        Object object = redisTemplate.opsForValue().get(key);
        if (object != null) {
            redisTemplate.delete(key);
        }
        return object;
    }

    /**
     * delete all like pattern
     *
     * @param pattern
     * @return
     */
    public void deleteAll(String pattern) {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

    /**
     * incr
     *
     * @param key
     * @return
     */
    public long incr(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * listLeftPush
     *
     * @param key
     * @return
     */
    public long listLeftPush(String key, String url) {
        return redisTemplate.opsForList().leftPush(key, url);
    }

    /**
     * listRightPush
     *
     * @param key
     * @return
     */
    public long listRightPush(String key, String url) {
        return redisTemplate.opsForList().rightPush(key, url);
    }

    /**
     * listRange
     *
     * @param key
     * @return
     */
    public List<Object> listRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * hashSetNx
     *
     * @param hash
     * @param key
     * @param value
     * @return
     */
    public boolean hashSetNx(String hash, String key, String value) {
        return redisTemplate.opsForHash().putIfAbsent(hash, key, value);
    }

    /**
     * hashSize
     *
     * @param hash
     * @return
     */
    public long hashSize(String hash) {
        return redisTemplate.opsForHash().size(hash);
    }

    /**
     * hashExist
     *
     * @param hash
     * @param key
     * @return
     */
    public boolean hashExist(String hash, String key) {
        return redisTemplate.opsForHash().hasKey(hash, key);
    }

    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

}
