package com.lincheng.study.service.impl;

import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.lincheng.study.entity.SeataProduct;
import com.lincheng.study.mapper.SeataProductMapper;
import com.lincheng.study.service.ISeataProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linCheng
 * @since 2021-12-20
 */
@Service
public class SeataProductServiceImpl extends ServiceImpl<SeataProductMapper, SeataProduct> implements ISeataProductService {

    @Resource
    private SeataProductMapper seataProductMapper;


    @Override
    public void testSeata(Long productId) {

        SeataProduct seataProduct = seataProductMapper.selectById(productId);
        seataProduct.setStockNum(seataProduct.getStockNum() - 1);

        Integer a = 1/0;

        seataProductMapper.updateById(seataProduct);

    }

}
