package com.lincheng.study.api;

import com.lincheng.study.api.product.IProductServiceApi;
import com.lincheng.study.entity.SeataProduct;
import com.lincheng.study.service.ISeataProductService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-03 15:40
 **/
//暴露接口
@DubboService
public class ProductServiceApiImpl implements IProductServiceApi {


    @Resource
    private ISeataProductService seataProductService;

    @Override
    public Object testDubbo(String source){
        return source + "测试product,dubbo成功";
    }

    @Override
    public void testSeata(Long productId) {
        seataProductService.testSeata(productId);
    }

}
