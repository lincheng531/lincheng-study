package com.lincheng.study.service;

import com.lincheng.study.domain.redis.TestRedisVO;

public interface ITestSpringCacheService {

    TestRedisVO testCacheable(TestRedisVO testRedisVO);

    TestRedisVO testCachePut(TestRedisVO testRedisVO);

    TestRedisVO testCacheEvict(TestRedisVO testRedisVO);

    TestRedisVO testCaching(TestRedisVO testRedisVO);
}
