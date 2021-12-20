package com.lincheng.study.service;

import com.lincheng.study.entity.SeataOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linCheng
 * @since 2021-12-20
 */
public interface ISeataOrderService extends IService<SeataOrder> {

    void saveSeataOrder();
}
