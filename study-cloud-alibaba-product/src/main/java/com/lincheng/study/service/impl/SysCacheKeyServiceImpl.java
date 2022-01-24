package com.lincheng.study.service.impl;

import com.lincheng.study.domain.product.ConfigureCacheVO;
import com.lincheng.study.entity.SysCacheKeyEntity;
import com.lincheng.study.mapper.SysCacheKeyMapper;
import com.lincheng.study.service.ISysCacheKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 缓存主表 服务实现类
 * </p>
 *
 * @author linCheng
 * @since 2022-01-24
 */
@Service
@CacheConfig(cacheNames = "ConfigureCache")
public class SysCacheKeyServiceImpl extends ServiceImpl<SysCacheKeyMapper, SysCacheKeyEntity> implements ISysCacheKeyService {

    @Resource
    private SysCacheKeyMapper sysCacheKeyMapper;


    @Override
    @Cacheable(key = " 'key:' + #businessCode")
    public Map<String, List<ConfigureCacheVO>> getConfigureCacheByBusinessCode(String businessCode){
        ConfigureCacheVO configureCacheVO = new ConfigureCacheVO();
        configureCacheVO.setBusinessCode(businessCode);
        List<ConfigureCacheVO> configureCacheList = sysCacheKeyMapper.queryConfigureCache(configureCacheVO);

        Map<String, List<ConfigureCacheVO>> resultMap = configureCacheList.stream().collect(Collectors.groupingBy(ConfigureCacheVO::getSubCode));

        return resultMap;
    }
}
