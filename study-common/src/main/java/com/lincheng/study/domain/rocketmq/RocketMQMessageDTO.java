package com.lincheng.study.domain.rocketmq;

import lombok.Data;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-15 22:09
 **/
@Data
public class RocketMQMessageDTO {

    private String topic;
    private String group;
    private String remark;
}
