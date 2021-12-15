package com.lincheng.study;

import com.lincheng.study.stream.ProductStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
//开启服务注册与发现功能
@EnableDiscoveryClient
@EnableBinding({ProductStream.class})
public class StudyCloudAlibabaProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyCloudAlibabaProductApplication.class, args);
    }

}
