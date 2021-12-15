package com.lincheng.study;

import com.lincheng.study.stream.ConsumerStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
//开启服务注册与发现功能
@EnableDiscoveryClient
@EnableBinding({ConsumerStream.class})
public class StudyCloudAlibabaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyCloudAlibabaConsumerApplication.class, args);
    }

}
