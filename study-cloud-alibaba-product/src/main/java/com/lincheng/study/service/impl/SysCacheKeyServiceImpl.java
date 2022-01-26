package com.lincheng.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.lincheng.study.domain.product.ConfigureCacheVO;
import com.lincheng.study.entity.SysCacheKeyEntity;
import com.lincheng.study.mapper.SysCacheKeyMapper;
import com.lincheng.study.service.ISysCacheKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lincheng.study.utils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Cacheable(key = " 'businessCode:' + #businessCode")
    public Map<String, List<ConfigureCacheVO>> getConfigureCacheByBusinessCode(String businessCode){
        ConfigureCacheVO configureCacheVO = new ConfigureCacheVO();
        configureCacheVO.setBusinessCode(businessCode);
        List<ConfigureCacheVO> configureCacheList = sysCacheKeyMapper.queryConfigureCache(configureCacheVO);

        return configureCacheList.stream().collect(Collectors.groupingBy(ConfigureCacheVO::getSubCode));
    }


    @Override
    @Cacheable(key = " 'businessCode:' + #businessCode + ':subCode:' + #subCode")
    public Map<String, List<ConfigureCacheVO>> getConfigureCacheByBusinessCodeAndSubCode(String businessCode,String subCode){
        ConfigureCacheVO configureCacheVO = new ConfigureCacheVO();
        configureCacheVO.setBusinessCode(businessCode);
        configureCacheVO.setSubCode(subCode);
        List<ConfigureCacheVO> configureCacheList = sysCacheKeyMapper.queryConfigureCache(configureCacheVO);

        Map<String, List<ConfigureCacheVO>> collect = configureCacheList.stream().collect(Collectors.groupingBy(ConfigureCacheVO::getSubCode));
        return collect;
    }


    @Override
    @Cacheable(key = " 'businessCode:' + #businessCode +  ':subCode:' + #subCode + ':keyCode:' + #keyCode")
    public String getConfigureCacheValue(String businessCode,String subCode,String keyCode){
        ConfigureCacheVO configureCacheVO = new ConfigureCacheVO();
        configureCacheVO.setBusinessCode(businessCode);
        configureCacheVO.setSubCode(subCode);
        configureCacheVO.setKeyCode(keyCode);
        List<ConfigureCacheVO> configureCacheList = sysCacheKeyMapper.queryConfigureCache(configureCacheVO);

        if (CollectionUtils.isNotEmpty(configureCacheList)){
            return configureCacheList.get(0).getKeyValue();
        }

        return null;
    }







}
