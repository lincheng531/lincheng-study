package com.lincheng.study.service;

import com.lincheng.study.entity.SeataProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linCheng
 * @since 2021-12-20
 */
public interface ISeataProductService extends IService<SeataProduct> {


    void testSeata(Long productId);
}
