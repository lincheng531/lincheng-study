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
     * @Description: springBoot配合化发送email
     * @author: linCheng
     * @Date: 2022/1/7 11:38
     * @param: fromEmail 发送者
     * @param: toUser 接收者
     * @param: subject 主题
     * @param: content 内容
     * @param: files 附件
     * @Return: java.lang.Boolean
     */
    public static Boolean sendMail(String fromEmail, List<String> toUser, String subject, String content, List<File> files) {
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


    public static JavaMailSenderImpl getJavaMailSenderImpl(String host,
                                                           String encoding,
                                                           String username,
                                                           String password) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setDefaultEncoding(encoding);
        sender.setUsername(username);
        sender.setPassword(password);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(properties);
        return sender;
    }


    /**
     * @Description:
     * @author: linCheng
     * @Date: 2022/3/23 10:43
     * @param: fromEmail 发送者
     * @param: toUser 接收者
     * @param: subject 主题
     * @param: content 内容
     * @param: files 附件
     * @param: host 服务地址 smtp.qq.com
     * @param: encoding 编码 UTF-8
     * @param: username 用户名 2826117968@qq.com
     * @param: password 密码（授权码）gvkpgdixvuiudfhf
     * @Return: java.lang.Boolean
     */
    public static Boolean sendMail(String fromEmail,
                                   List<String> toUser,
                                   String subject,
                                   String content,
                                   List<File> files,
                                   String host,
                                   String encoding,
                                   String username,
                                   String password) {

        JavaMailSenderImpl mailSender = getJavaMailSenderImpl(host, encoding, username, password);

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

}
