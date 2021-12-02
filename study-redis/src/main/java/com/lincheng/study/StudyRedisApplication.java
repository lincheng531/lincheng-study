package com.lincheng.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//开启缓存
@EnableCaching
public class StudyRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyRedisApplication.class, args);
    }

}
