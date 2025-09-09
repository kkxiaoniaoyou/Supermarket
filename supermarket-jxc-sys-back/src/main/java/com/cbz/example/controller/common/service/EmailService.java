package com.cbz.example.controller.common.service;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

/**
 * @author 11272
 */
public interface EmailService {

    /**
     * 发送邮箱验证码
     * @param to '
     * @param title '
     * @param content '
     */
    void sendSimpleMail(String to,String title,String content) throws MessagingException;

    void sendAttachmentsMail(String to, String title, String content, List<File> fileList);
}
