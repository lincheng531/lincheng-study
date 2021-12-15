package com.lincheng.study.controller;

import com.lincheng.study.domain.rocketmq.RocketMQMessageDTO;
import com.lincheng.study.stream.ProductStream;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linCheng
 * @date 2021/5/26 13:52
 */
@RestController
@RequestMapping("/rocketMq")
public class TestRocketMQController {

    @Resource
    private ProductStream productStream;

    @Resource
    private MessageChannel output; // 获取name为output的binding

    @RequestMapping("/productOutput")
    public void productOutput(){
        RocketMQMessageDTO rocketMQMessageDTO = new RocketMQMessageDTO();
        rocketMQMessageDTO.setGroup("no group");
        rocketMQMessageDTO.setTopic("Output");
        rocketMQMessageDTO.setRemark("测试消息中间件Output");

        Message<RocketMQMessageDTO> messageVOMessage = MessageBuilder.withPayload(rocketMQMessageDTO)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();

        productStream.productOutput().send(messageVOMessage);
    }


}
