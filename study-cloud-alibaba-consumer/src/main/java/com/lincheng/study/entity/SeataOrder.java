package com.lincheng.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author linCheng
 * @since 2021-12-20
 */
@Getter
@Setter
@TableName("seata_order")
public class SeataOrder extends Model<SeataOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ORDER_ID", type = IdType.AUTO)
    private Long orderId;

    @TableField("ORDER_NUM")
    private Integer orderNum;

    @TableField("PRODUCT_ID")
    private Long productId;


    @Override
    public Serializable pkVal() {
        return this.orderId;
    }

}
