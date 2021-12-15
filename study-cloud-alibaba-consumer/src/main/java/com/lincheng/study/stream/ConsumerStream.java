package com.lincheng.study.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-15 22:03
 **/
public interface ConsumerStream {

    @Input("output")
    SubscribableChannel consumerOutput();

}
