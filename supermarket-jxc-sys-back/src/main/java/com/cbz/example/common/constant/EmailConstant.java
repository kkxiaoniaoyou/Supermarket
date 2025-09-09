package com.cbz.example.common.constant;

public class EmailConstant {

    /**
     * 邮件模板
     * 替换里面的 ${REPLACE_CODE}
     */
    public static final String EMAIL_MESSAGE_TEMPLATE = "    <div style=\"display: flex;justify-content: center;\">\n" +
            "        <div style=\"padding: 75px; width: 400px;background-color: #1D2033;color:white;\">\n" +
            "            <p style=\"font-size: 20px;\">欢迎您注册Rabbit云盘账号!</p>\n" +
            "            <p>您的Rabbit云盘注册验证码为:</p>\n" +
            "            <div style=\"font-size: 42px;color: #3A9AED;\">${REPLACE_CODE}</div>\n" +
            "            <p>如果您没有请求此代码,请忽略本邮件</p>\n" +
            "            <div style=\"height: 1px;background-color: gray;\"></div>\n" +
            "            <p>祝您使用愉快!</p>\n" +
            "            <p>Rabbit云盘团队</p>\n" +
            "        </div>\n" +
            "    </div>";
}
