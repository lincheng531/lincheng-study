package com.lincheng.study.service;

import com.lincheng.study.domain.product.ConfigureCacheVO;
import com.lincheng.study.entity.SysCacheKeyEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 缓存主表 服务类
 * </p>
 *
 * @author linCheng
 * @since 2022-01-24
 */
public interface ISysCacheKeyService extends IService<SysCacheKeyEntity> {

    Map<String, List<ConfigureCacheVO>> getConfigureCacheByBusinessCode(String businessCode);

    Map<String, List<ConfigureCacheVO>> getConfigureCacheByBusinessCodeAndSubCode(String businessCode,String subCode);

    String getConfigureCacheValue(String businessCode,String subCode,String keyCode);
}
