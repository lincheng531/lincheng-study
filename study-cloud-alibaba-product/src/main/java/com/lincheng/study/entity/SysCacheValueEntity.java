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
 * 缓存值表
 * </p>
 *
 * @author linCheng
 * @since 2022-01-24
 */
@Getter
@Setter
@TableName("sys_cache_value")
public class SysCacheValueEntity extends Model<SysCacheValueEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("param_id")
    private String paramId;

    /**
     * 键

     */
    @TableField("key_code")
    private String keyCode;

    /**
     * 值编码
     */
    @TableField("key_value")
    private String keyValue;

    /**
     * 顺序
     */
    @TableField("sequence")
    private Integer sequence;

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
        return this.keyCode;
    }

}
