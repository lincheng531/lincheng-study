package com.lincheng.study.service.impl;

import com.lincheng.study.domain.redis.TestRedisVO;
import com.lincheng.study.service.ITestSpringCacheService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-02 11:06
 **/
@Service
//一次性声明完,这个方法中的缓存都会加上cacheNames中的key
@CacheConfig(cacheNames = "SpringCache")
public class TestSpringCacheServiceImpl implements ITestSpringCacheService {


    private TestRedisVO testRedisVO;

    /**
     * @Description: 主要用在查询
     * 1、Cacheable中配置了cacheNames ,那CacheConfig中的cacheNames就失效了。
     * 2、key 使用SpEL表达式 官方文档：https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache
     * 3、condition：只有满足表达式条件的内容才会被缓存
     * 4、unless：另外一个缓存条件参数 它不同于condition参数的地方在于它的判断时机，该条件是在函数被调用之后才做判断的，所以它可以通过对result进行判断
     * @author: linCheng
     * @Date: 2021/12/2 15:10
     * @param: testRedisVO
     * @Return: com.lincheng.study.domain.redis.TestRedisVO
     */
    @Override
    @Cacheable(key = " 'Test:' + 'key:' + #testRedisVO.key")
    public TestRedisVO testCacheable(TestRedisVO testRedisVO) {
        testRedisVO.setPropertys(Arrays.asList("admin", "superAdmin"));
        testRedisVO.setSize(200L);
        testRedisVO.setValue("Cacheable");
        return testRedisVO;
    }


    /**
     * @Description: 主要用在修改
     * CachePut与Cacheable 不同的是，它每次都会触发真实方法的调用
     * @author: linCheng
     * @Date: 2021/12/2 15:21
     * @param: testRedisVO
     * @Return: com.lincheng.study.domain.redis.TestRedisVO
     */
    @Override
    @CachePut(key = "'Test:' + 'key:' + #testRedisVO.key")
    public TestRedisVO testCachePut(TestRedisVO testRedisVO) {
        testRedisVO.setPropertys(Collections.singletonList("admin"));
        testRedisVO.setSize(288L);
        testRedisVO.setValue("CachePut");
        return testRedisVO;
    }


    /**
     * @Description: 主要用在批量修改，批量保存，删除中
     * condition：缓存的条件，返回 true 或者 false，只有为 true 才进行删除
     * allEntries：缺省时为 false， 是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存
     * beforeInvocation：缺省时为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存
     * @author: linCheng
     * @Date: 2021/12/2 15:35
     * @param: testRedisVO
     * @Return: com.lincheng.study.domain.redis.TestRedisVO
     */
    @Override
    @CacheEvict(key = "'Test:' + 'key:' + #testRedisVO.key", allEntries = true, beforeInvocation = true)
    public TestRedisVO testCacheEvict(TestRedisVO testRedisVO) {
        testRedisVO.setPropertys(Arrays.asList("admin", "superAdmin", "normal"));
        testRedisVO.setSize(666L);
        testRedisVO.setValue("CacheEvict");
        return testRedisVO;
    }


    @Override
    @Caching(put = {
            @CachePut(key = "'Test:' + 'key:' + #testRedisVO.key"),
            @CachePut(key = "'Test:' + 'size:' + #testRedisVO.size"),
            @CachePut(key = "'Test:' + 'value:' + #testRedisVO.value")
    })
    public TestRedisVO testCaching(TestRedisVO testRedisVO) {
        this.testRedisVO = testRedisVO;
        testRedisVO.setPropertys(Arrays.asList("admin", "superAdmin", "normal"));
        testRedisVO.setSize(999L);
        testRedisVO.setValue("Caching");
        return testRedisVO;
    }


}
