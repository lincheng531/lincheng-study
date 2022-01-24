package com.lincheng.study.mapper;

import com.lincheng.study.domain.product.ConfigureCacheVO;
import com.lincheng.study.entity.SysCacheKeyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 缓存主表 Mapper 接口
 * </p>
 *
 * @author linCheng
 * @since 2022-01-24
 */
@Mapper
public interface SysCacheKeyMapper extends BaseMapper<SysCacheKeyEntity> {

    List<ConfigureCacheVO> queryConfigureCache(ConfigureCacheVO configureCacheVO);


}
