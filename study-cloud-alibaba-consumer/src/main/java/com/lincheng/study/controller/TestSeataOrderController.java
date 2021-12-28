package com.lincheng.study.controller;


import com.lincheng.study.service.ISeataOrderService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linCheng
 * @since 2021-12-20
 */
@RestController
@RequestMapping("/testSeata")
public class TestSeataOrderController {

    @Resource
    private ISeataOrderService seataOrderService;

    @RequestMapping("/saveSeataOrder")
    public void saveSeataOrder(){
        seataOrderService.saveSeataOrder();
    }

}
