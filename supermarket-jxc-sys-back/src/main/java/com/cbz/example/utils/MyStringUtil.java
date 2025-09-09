package com.cbz.example.utils;

import java.util.Random;

/**
 * 本地的字符串操作类
 */
public class MyStringUtil {

    /**
     * 生成验证码字符串  4位
     * @return
     */
    public static String generateCaptcha() {

        // 定义包含数字和小写字母的字符数组
        char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // 生成四位随机字符
        for (int i = 0; i < 4; i++) {
            char randomChar = chars[random.nextInt(chars.length)];
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
