package com.lincheng.study.controller;


import com.lincheng.study.service.ISysCacheKeyService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 缓存主表 前端控制器
 * </p>
 *
 * @author linCheng
 * @since 2022-01-24
 */
@RestController
@RequestMapping("/configureCache")
public class SysCacheKeyController {

    @Resource
    private ISysCacheKeyService sysCacheKeyService;


    @RequestMapping("/getCacheByBusinessCode")
    public Object getConfigureCacheByBusinessCode(@RequestParam("businessCode") String businessCode) {
        return sysCacheKeyService.getConfigureCacheByBusinessCode(businessCode);
    }


    @RequestMapping("/getCacheByBusinessCodeAndSubcode")
    public Object getCacheByBusinessCodeAndSubcode(@RequestParam("businessCode") String businessCode, @RequestParam("subCode") String subCode) {
        return sysCacheKeyService.getConfigureCacheByBusinessCodeAndSubCode(businessCode, subCode);
    }


    @RequestMapping("/getConfigureCacheValue")
    public Object getConfigureCacheValue(@RequestParam("businessCode") String businessCode,
                                                   @RequestParam("subCode") String subCode,
                                                   @RequestParam("keyCode") String keyCode) {
        return sysCacheKeyService.getConfigureCacheValue(businessCode, subCode,keyCode);
    }
}
