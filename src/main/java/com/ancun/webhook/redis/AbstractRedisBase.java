package com.ancun.webhook.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/2/7
 */
public abstract class AbstractRedisBase<T> {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;
    @Resource
    protected HashOperations<String, String, T> hashOperations;

    /**
     * 添加
     *
     * @param redisKey
     * @param hashKey
     * @param doamin
     * @param expire   过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */
    public void put(String redisKey, String hashKey, T doamin, long expire) {
        hashOperations.put(redisKey, hashKey, doamin);
        if (expire != -1) {
            redisTemplate.expire(redisKey, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除
     *
     * @param redisKey
     * @param hashKey
     */
    public void remove(String redisKey, String hashKey) {
        hashOperations.delete(redisKey, hashKey);
    }

    /**
     * 查询
     *
     * @param redisKey
     * @param hashKey
     * @return
     */
    public T get(String redisKey, String hashKey) {
        return hashOperations.get(redisKey, hashKey);
    }

    /**
     * 获取当前redisKey下所有对象
     *
     * @param redisKey
     * @return
     */
    public List<T> getAll(String redisKey) {
        return hashOperations.values(redisKey);
    }

    /**
     * 查询查询当前redis库下所有key
     *
     * @param redisKey
     * @return
     */
    public Set<String> getKeys(String redisKey) {
        return hashOperations.keys(redisKey);
    }

    /**
     * 判断key是否存在redis中
     *
     * @param redisKey
     * @param hashKey
     * @return
     */
    public boolean isKeyExists(String redisKey, String hashKey) {
        return hashOperations.hasKey(redisKey, hashKey);
    }

    /**
     * 查询当前key下缓存数量
     *
     * @param redisKey
     * @return
     */
    public long count(String redisKey) {
        return hashOperations.size(redisKey);
    }

    /**
     * 清空redis
     *
     * @param redisKey
     */
    public void empty(String redisKey) {
        Set<String> set = hashOperations.keys(redisKey);
        set.stream().forEach(key -> hashOperations.delete(redisKey, key));
    }

    /**
     * 删除redisKey
     *
     * @param redisKey
     */
    public void delete(String redisKey) {
        redisTemplate.delete(redisKey);
    }


    /**
     * 是否存在
     *
     * @param redisKey
     * @return
     */
    public boolean isExistRedisKey(String redisKey) {
        return redisTemplate.hasKey(redisKey);
    }


}