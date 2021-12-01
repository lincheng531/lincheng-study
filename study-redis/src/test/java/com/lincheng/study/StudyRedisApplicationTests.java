package com.lincheng.study;

import com.lincheng.study.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyRedisApplication.class)
public class StudyRedisApplicationTests {

    @Test
    public void contextLoads() {
        Object age = RedisUtils.get("age");
        System.out.println(age);
    }

}
