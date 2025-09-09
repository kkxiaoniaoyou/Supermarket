package com.cbz.example.controller.common;


import com.cbz.example.common.Result;
import com.cbz.example.utils.MyStringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * 验证码控制器
 */
@Controller
@RequiredArgsConstructor
public class CaptchaController {

    @Value("${captcha.redis.key-prefix}")
    private String keyPrefix;

    @Value("${captcha.redis.expire-time}")
    private long expirationTime;


    private final RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
        GifCaptcha specCaptcha = new GifCaptcha(130, 48, 5);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.FONT_1);

        redisTemplate.opsForValue().set(keyPrefix, specCaptcha.text().toLowerCase(), expirationTime, TimeUnit.SECONDS);

//         验证码存入session
//        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());

        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }

    @RequestMapping("/captchaNbr")
    public void captchaNbr(HttpServletResponse response) throws Exception {

        String captcha = MyStringUtil.generateCaptcha();

        redisTemplate.opsForValue().set(keyPrefix, captcha, expirationTime, TimeUnit.SECONDS);

        // 将 Result 对象转换成 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = objectMapper.writeValueAsString(Result.success("操作成功", captcha));

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(resultJson);
    }
}
