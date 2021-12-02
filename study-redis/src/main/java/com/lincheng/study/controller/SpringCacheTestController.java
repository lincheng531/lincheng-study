package com.lincheng.study.controller;

import com.lincheng.study.domain.redis.TestRedisVO;
import com.lincheng.study.service.interfaces.ITestSpringCacheService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-02 11:26
 **/
@RestController
@RequestMapping("/cache")
public class SpringCacheTestController {

    @Resource
    private ITestSpringCacheService testSpringCacheService;

    @RequestMapping("/testCacheable")
    public Object testCacheable(){
        TestRedisVO testRedisVO = new TestRedisVO();
        testRedisVO.setKey(2L);
        TestRedisVO result = testSpringCacheService.testCacheable(testRedisVO);
        return result;
    }


    @RequestMapping("/testCachePut")
    public Object testCachePut(){
        TestRedisVO testRedisVO = new TestRedisVO();
        testRedisVO.setKey(2L);
        TestRedisVO redisVO = testSpringCacheService.testCachePut(testRedisVO);
        return redisVO;
    }



    @RequestMapping("/testCacheEvict")
    public Object testCacheEvict(){
        TestRedisVO testRedisVO = new TestRedisVO();
        testRedisVO.setKey(2L);
        TestRedisVO result = testSpringCacheService.testCacheEvict(testRedisVO);
        return result;
    }




    @RequestMapping("/testCaching")
    public Object testCaching(){
        TestRedisVO testRedisVO = new TestRedisVO();
        testRedisVO.setKey(2L);
        TestRedisVO result = testSpringCacheService.testCaching(testRedisVO);
        return result;
    }



}
