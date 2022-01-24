package com.lincheng.study.domain.product;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author lincheng5
 * @date 2022/1/24 21:15
 */
@Data
public class ConfigureCacheVO implements Serializable {

    private static final long serialVersionUID = -1205795530969679766L;

    /**
     * 主键
     */
    private String paramId;

    /**
     * 模块
     */
    private String centerCode;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 类别编码
     */
    private String subCode;

    /**
     * 键编码
     */
    private String keyCode;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 状态
     */
    private String state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Timestamp createTime;


    /**
     * 值编码
     */
    private String keyValue;

    /**
     * 顺序
     */
    private Integer sequence;

}
