package com.lincheng.study.stream;

import com.lincheng.study.domain.rocketmq.RocketMQMessageDTO;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-15 22:07
 **/
@Component
public class ConsumerStreamListener {

    @StreamListener("output")
    public void onMessage(@Payload RocketMQMessageDTO message) {
        System.out.println("消费mq");
        System.out.println("线程编号:" + Thread.currentThread().getId());
        System.out.println("消息内容:" + message);
    }

}
