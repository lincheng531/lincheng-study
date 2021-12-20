package com.lincheng.study.service.impl;

import com.lincheng.study.api.product.IProductServiceApi;
import com.lincheng.study.entity.SeataOrder;
import com.lincheng.study.mapper.SeataOrderMapper;
import com.lincheng.study.service.ISeataOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linCheng
 * @since 2021-12-20
 */
@Service
public class SeataOrderServiceImpl extends ServiceImpl<SeataOrderMapper, SeataOrder> implements ISeataOrderService {

    @Resource
    private SeataOrderMapper seataOrderMapper;

    @DubboReference(check = false, timeout = 5 * 1000, retries = 0)
    private IProductServiceApi productServiceApi;


    @Override
    @GlobalTransactional
    public void saveSeataOrder(){
        SeataOrder seataOrder = new SeataOrder();
        seataOrder.setOrderNum(1);
        seataOrder.setProductId(1L);
        seataOrderMapper.insert(seataOrder);

        productServiceApi.testSeata(1L);
    }

}
