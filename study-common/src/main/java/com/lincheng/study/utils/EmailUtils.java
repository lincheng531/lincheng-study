package com.lincheng.study.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * @description: 邮箱工具类
 * @author: linCheng
 * @create: 2022-01-07 11:30
 **/
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
        }
        return true;
    }

}
