package com.lincheng.study.controller;

import com.lincheng.study.api.product.IProductServiceApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-03 15:46
 **/
@RestController
@RequestMapping("/testDubbo")
public class TestDubboController {

    @DubboReference(check = false ,timeout = 5 * 1000 ,retries = 0)
    private IProductServiceApi productServiceApi;

    @RequestMapping("/transferProduct")
    public Object transferProduct(){
        return productServiceApi.testDubbo("consumer");
    }


}
