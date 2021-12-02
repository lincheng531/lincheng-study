package com.lincheng.study.controller;

import com.lincheng.study.utils.RedisUtils;
import com.lincheng.study.utils.RedissonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-01 17:21
 **/
@RestController
public class RedissonTestController {

    @RequestMapping(value = "/testRedissonUtil")
    public Object testRedissonUtil() throws Exception{
        return RedissonUtils.getString("age");
    }

    @RequestMapping(value = "/testRedisUtil")
    public Object testRedisUtil() throws Exception{
        return RedisUtils.get("age");
    }


}
