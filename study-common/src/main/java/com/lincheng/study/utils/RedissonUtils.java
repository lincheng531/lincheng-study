package com.lincheng.study.utils;

import com.alibaba.nacos.common.utils.MapUtils;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-11-17 17:04
 **/
@Component
public class RedissonUtils {

    private static RedissonClient redissonClient;

    @Resource
    private RedissonClient client;

    @PostConstruct
    public void init() {
        redissonClient = client;
    }


    public static Object getString(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        return bucket.get();
    }


    public static void setString(String key, Object value, Long time) {
        RBucket<Object> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value, time, TimeUnit.SECONDS);
    }


    public static void setString(String key, Object value) {
        RBucket<Object> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value);
    }


    public static HashMap<Object, Object> getHash(String key) {
        RMap<Object, Object> map = redissonClient.getMap(key);
        return new HashMap<>(map);
    }


    public static Object getHash(String key, Object mapKey) {
        RMap<Object, Object> map = redissonClient.getMap(key);
        return map.get(mapKey);
    }


    public static void setHash(String key, HashMap<Object, Object> value) {
        if (MapUtils.isNotEmpty(value)) {
            RMap<Object, Object> map = redissonClient.getMap(key);
            value.forEach(map::put);
        }
    }


    public static void setHash(String key, HashMap<Object, Object> value, Long time) {
        if (MapUtils.isNotEmpty(value)) {
            RMap<Object, Object> map = redissonClient.getMap(key);
            value.forEach(map::put);
            map.expire(time, TimeUnit.SECONDS);
        }
    }


    public static void setList(String key, List<Object> value) {
        RList<Object> rList = redissonClient.getList(key);
        rList.addAll(value);
    }


    public static void setList(String key, List<Object> value, Long time) {
        RList<Object> rList = redissonClient.getList(key);
        rList.addAll(value);
        rList.expire(time, TimeUnit.SECONDS);
    }


    public static List<Object> getList(String key) {
        return redissonClient.getList(key);
    }


    public static void setSets(String key, Set<Object> value) {
        RSet<Object> rSet = redissonClient.getSet(key);
        rSet.addAll(value);
    }


    public static void setSets(String key, List<Object> value, Long time) {
        RSet<Object> rSet = redissonClient.getSet(key);
        rSet.addAll(value);
        rSet.expire(time, TimeUnit.SECONDS);
    }


    public static Set<Object> getSets(String key) {
        return redissonClient.getSortedSet(key);
    }


    public static Long deleteByKey(String... key) {
        RKeys keys = redissonClient.getKeys();
        return keys.delete(key);
    }


    public static Long deleteAll(String key) {
        RKeys keys = redissonClient.getKeys();
        return keys.deleteByPattern(key);
    }

    /**
     * @Description: 获取锁
     * @author: linCheng
     * @Date: 2021/11/18 16:09
     * @param: objectName
     * @Return: org.redisson.api.RLock
     */
    public static RLock getRLock(String objectName) {
        return redissonClient.getLock(objectName);
    }


}
