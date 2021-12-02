package com.lincheng.study.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-02 10:36
 **/
@Configuration
//开启缓存，如果没有使用缓存中间件会使用Spring自带的缓存，否则使用中间件缓存，这里就会使用redis缓存中间件
@EnableCaching
public class SpringCacheConfig {


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                //默认策略，为配置的key会使用这个(10分钟失效)
                this.getRedisCacheConfigurationWithTtl(600),
                //指定key策略
                this.getRedisCacheConfigurationMap()
        );
    }


    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration
                //超时时间
                .entryTtl(Duration.ofSeconds(seconds))
                //如果是空值，不缓存(这里如果打开，意思就是空值不缓存，当存的值为空时，会抛出异常;所以这望允许存空值)
                //.disableCachingNullValues()
                //变双冒号为单冒号
                .computePrefixWith(name -> name + ":")
                //key序列化
                .serializeKeysWith(keyPair())
                //value序列化
                .serializeValuesWith(valuePair());
        return redisCacheConfiguration;
    }


    private RedisSerializationContext.SerializationPair<String> keyPair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
    }


    private RedisSerializationContext.SerializationPair<Object> valuePair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
    }


    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        // 需要作缓存在这里加上就加一个put即可
        redisCacheConfigurationMap.put("bankId", this.getRedisCacheConfigurationWithTtl(120)); // 120秒后失效
        return redisCacheConfigurationMap;
    }

}
