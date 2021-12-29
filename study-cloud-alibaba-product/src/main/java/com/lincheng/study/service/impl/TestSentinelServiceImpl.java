package com.lincheng.study.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lincheng.study.service.TestSentinelService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-29 17:05
 **/
@Service
public class TestSentinelServiceImpl implements TestSentinelService {


    @SentinelResource(blockHandler = "flowblockHandler")
    public String testLinkService(String api){
        return "testLink:" + api;
    }


    public String flowblockHandler(String api,BlockException ex) {
        return "链路流控！！！！！！! ! " ;
    }



}
