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
 * @since 2022-01-07
 */
@Getter
@Setter
@TableName("sys_mail_config")
public class MailConfig extends Model<MailConfig> {

    private static final long serialVersionUID = -169939614347109643L;

    @TableId(value = "mail_config_id", type = IdType.AUTO)
    private Long mailConfigId;

    @TableField("host")
    private String host;

    @TableField("encoding")
    private String encoding;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("subject")
    private String subject;

    @TableField("content")
    private String content;


    @Override
    public Serializable pkVal() {
        return this.mailConfigId;
    }

}
