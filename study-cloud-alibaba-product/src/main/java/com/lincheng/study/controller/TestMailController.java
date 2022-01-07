package com.lincheng.study.controller;

import com.lincheng.study.utils.EmailUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @description:
 * @author: linCheng
 * @create: 2022-01-07 16:12
 **/
@RestController
@RequestMapping("/mail")
public class TestMailController {

    @RequestMapping("/sendAttachmentMail")
    public Object sendAttachmentMail() {
        return EmailUtils.sendAttachmentMail("2826117968@qq.com", Arrays.asList("924093347@qq.com", "654014090@qq.com"), "测试邮箱-subject", "测试邮箱", null);
    }
}
