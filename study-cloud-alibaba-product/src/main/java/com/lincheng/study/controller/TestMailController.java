package com.lincheng.study.controller;

import com.lincheng.study.entity.MailConfig;
import com.lincheng.study.service.IMailConfigService;
import com.lincheng.study.service.impl.MailConfigServiceImpl;
import com.lincheng.study.utils.EmailUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @description:
 * @author: linCheng
 * @create: 2022-01-07 16:12
 **/
@RestController
@RequestMapping("/mail")
public class TestMailController {

    @Resource
    private IMailConfigService mailConfigService;

    @RequestMapping("/testSendMail")
    public Object testSendMail() {

        MailConfig mailConfig = mailConfigService.getById(1);

        Boolean aBoolean = EmailUtils.sendMail(mailConfig.getUsername(),
                Arrays.asList("924093347@qq.com", "654014090@qq.com"),
                mailConfig.getSubject(),
                mailConfig.getContent(),
                null,
                mailConfig.getHost(),
                mailConfig.getEncoding(),
                mailConfig.getUsername(),
                mailConfig.getPassword());
        return aBoolean;
    }


    @RequestMapping("/sendMailByApplication")
    public Object sendMailByApplication() {
        EmailUtils.sendMail("2826117968@qq.com", Arrays.asList("924093347@qq.com", "654014090@qq.com"), "测试邮箱-subject", "测试邮箱", null);
        return true;
    }
}
