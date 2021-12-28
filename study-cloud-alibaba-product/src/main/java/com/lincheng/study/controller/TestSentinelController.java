package com.lincheng.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-28 15:44
 **/
@RestController
@RequestMapping("/sentinel")
public class TestSentinelController {

    @RequestMapping("/testQBS")
    //@SentinelResource(blockHandler = "flowblockHandler")
    public String testQBS() {
        return "testQBS";
    }


    @RequestMapping("/testThread")
    //@SentinelResource(blockHandler = "flowblockHandler")
    public String testThread() throws InterruptedException {
        Thread.sleep(2000);
        return "testThread";
    }


    public String flowblockHandler(BlockException ex) {
        return "流控! ! " ;
    }
}
