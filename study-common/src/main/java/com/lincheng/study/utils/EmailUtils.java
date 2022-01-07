package com.lincheng.study.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @description: 邮箱工具类
 * @author: linCheng
 * @create: 2022-01-07 11:30
 **/
@Component
public class EmailUtils {

    private static JavaMailSender javaMailSender;

    @Resource
    private JavaMailSender mailSender;

    @PostConstruct
    public void init() {
        javaMailSender = mailSender;
    }


    /**
     * @Description:
     * @author: linCheng
     * @Date: 2022/1/7 11:38
     * @param: fromEmail 发送者
     * @param: toUser 接收者
     * @param: subject 主题
     * @param: content 内容
     * @param: files 附件
     * @Return: java.lang.Boolean
     */
    public static Boolean sendAttachmentMail(String fromEmail, List<String> toUser, String subject, String content, List<File> files) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(toUser.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content);
            helper.setFrom(fromEmail);

            if (CollectionUtils.isNotEmpty(files)) {
                for (File file : files) {
                    FileSystemResource fileSystemResource = new FileSystemResource(file);
                    //附件
                    helper.addAttachment(file.getName(), fileSystemResource);
                }
            }

            //发送邮件
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static JavaMailSenderImpl getJavaMailSenderImpl(){

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.qq.com");
        sender.setDefaultEncoding("UTF-8");
        sender.setUsername("2826117968@qq.com");
        sender.setPassword("gvkpgdixvuiudfhf");
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(p);
        return sender;
    }


    public static Boolean sendMail(String fromEmail, List<String> toUser, String subject, String content, List<File> files) {

        JavaMailSenderImpl mailSender = getJavaMailSenderImpl();

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(toUser.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content);
            helper.setFrom(fromEmail);

            if (CollectionUtils.isNotEmpty(files)) {
                for (File file : files) {
                    FileSystemResource fileSystemResource = new FileSystemResource(file);
                    //附件
                    helper.addAttachment(file.getName(), fileSystemResource);
                }
            }

            //发送邮件
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void main(String[] args){
        sendMail("2826117968@qq.com", Arrays.asList("924093347@qq.com", "654014090@qq.com"), "测试邮箱-subject", "测试邮箱", null);
    }

}
