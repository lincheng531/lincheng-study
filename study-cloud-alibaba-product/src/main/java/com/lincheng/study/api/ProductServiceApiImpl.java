package com.lincheng.study.api;

import com.lincheng.study.api.product.IProductServiceApi;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-03 15:40
 **/
//暴露接口
@DubboService
public class ProductServiceApiImpl implements IProductServiceApi {

    @Override
    public Object testDubbo(String source){
        return source + "测试product,dubbo成功";
    }
}
