package com.lincheng.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-02 23:56
 **/
@RestController
//更改配置文件，程序不用重启，就可以读取更新后的配置数据
@RefreshScope
public class TestConfigController {

    @Value("${test.alibaba.config}")
    public String configName;

    @Value("${test.all}")
    public String all;

    @RequestMapping("/testAlibabaConfig")
    public Object testAlibabaConfig(){
        return Arrays.asList(configName,all);
    }
}
