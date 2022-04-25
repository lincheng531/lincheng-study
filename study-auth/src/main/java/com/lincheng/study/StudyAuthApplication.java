package com.lincheng.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class StudyAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyAuthApplication.class, args);
    }

}
