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
@TableName("seata_product")
public class SeataProduct extends Model<SeataProduct> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PRODUCT_ID", type = IdType.AUTO)
    private Long productId;

    @TableField("PRODUCT_NAME")
    private String productName;

    @TableField("STOCK_NUM")
    private Integer stockNum;


    @Override
    public Serializable pkVal() {
        return this.productId;
    }

}
