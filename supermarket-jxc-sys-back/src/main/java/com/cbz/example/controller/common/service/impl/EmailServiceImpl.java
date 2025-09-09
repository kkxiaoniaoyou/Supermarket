package com.cbz.example.controller.common.service.impl;

import com.cbz.example.controller.common.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;

/**
 * @author 11272
 * 邮箱服务
 **/
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String to, String title, String content) throws MessagingException {

        // 发送带有html页面的信息
        MimeMessage mimeMailMessage = this.mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMailMessage, true, "utf-8");
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(title);
        messageHelper.setText(content, true);
        mailSender.send(mimeMailMessage);
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(title);
//        message.setText(content);
//        mailSender.send(message);

        System.out.println("邮件发送成功");
    }

    @Override
    public void sendAttachmentsMail(String to, String title, String content, List<File> fileList) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content);
            String fileName = null;
            for (File file : fileList) {
                fileName = MimeUtility.encodeText(file.getName(), "GB2312", "B");
                helper.addAttachment(fileName, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);

        System.out.println("邮件发送成功");
    }
}
