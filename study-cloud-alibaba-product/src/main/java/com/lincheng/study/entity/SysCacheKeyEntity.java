package com.lincheng.study.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 缓存主表
 * </p>
 *
 * @author linCheng
 * @since 2022-01-24
 */
@Getter
@Setter
@TableName("sys_cache_key")
public class SysCacheKeyEntity extends Model<SysCacheKeyEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("param_id")
    private String paramId;

    /**
     * 模块
     */
    @TableField("center_code")
    private String centerCode;

    /**
     * 业务编码
     */
    @TableField("business_code")
    private String businessCode;

    /**
     * 类别编码
     */
    @TableField("sub_code")
    private String subCode;

    /**
     * 键值
     */
    @TableField("param_value")
    private String paramValue;

    /**
     * 状态
     */
    @TableField("state")
    private String state;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


    @Override
    public Serializable pkVal() {
        return this.subCode;
    }

}
