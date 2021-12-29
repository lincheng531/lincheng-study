package com.lincheng.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lincheng.study.service.TestSentinelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-28 15:44
 **/
@RestController
@RequestMapping("/sentinel")
public class TestSentinelController {


    @Resource
    private TestSentinelService testSentinelService;


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


    @RequestMapping("/testAdd")
    public String testAdd() throws InterruptedException {
        return "testAdd";
    }


    @RequestMapping("/testSelect")
    public String testSelect() throws InterruptedException {
        return "testSelect";
    }


    @RequestMapping("/testLinkOne")
    public String testLinkOne() throws InterruptedException {
        return testSentinelService.testLinkService("testLinkOne");
    }


    @RequestMapping("/testLinkTow")
    public String testLinkTow() throws InterruptedException {
        return testSentinelService.testLinkService("testLinkTow");
    }


    public String flowblockHandler(BlockException ex) {
        return "流控! ! ";
    }
}
