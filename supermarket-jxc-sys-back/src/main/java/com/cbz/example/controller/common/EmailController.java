package com.cbz.example.controller.common;

import cn.hutool.core.util.RandomUtil;
import com.cbz.example.common.Result;
import com.cbz.example.common.constant.EmailConstant;
import com.cbz.example.common.domain.SendEmail;
import com.cbz.example.controller.common.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 电子邮箱服务
 * @author 11272
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮箱
     */
    @PostMapping("/send")
    public Result<Boolean> send(@RequestBody SendEmail sendEmail) {

        String message = EmailConstant.EMAIL_MESSAGE_TEMPLATE;

        String captcha = RandomUtil.randomString(6);

        message = message.replace("${REPLACE_CODE}", captcha);

        try {
            emailService.sendSimpleMail(sendEmail.getSendTo(), "注册验证码", message);
        }catch (Exception e){
            return Result.fail(601, "发送失败", false);
        }
        return Result.success();
    }
}
