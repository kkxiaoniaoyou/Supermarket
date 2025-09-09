package com.cbz.example.exception.enums;

/**
 * 业务异常枚举
 *
 * @author 11272
 * @date 2024/1/10 18:19
 **/
public enum BusinessExceptionEnum {

    // 示例业务异常枚举
    INVALID_PARAMETER(4001, "无效的参数"),
    // 请求头缺失
    TOKEN_HEADER_NEED(4011, "缺失Authorization请求头"),
    // 用户名已经存在
    USERNAME_EXIST(61002, "用户名已经存在"),
    PHONE_EXIST(61005, "手机号已经存在"),
    EMAIL_EXIST(61008, "邮箱已经存在"),
    // 验证码不匹配
    CAPTCHA_UN_MATCH(60022, "验证码不匹配"),
    // 用户名或密码不匹配
    USERNAME_PASSWORD_UN_MATCH(60033, "用户名或密码不匹配"),
    // 令牌已过期
    TOKEN_EXPIRED(620001, "令牌已过期，请重新登录"),
    // 令牌格式错误
    TOKEN_FORMAT_ERROR(620012, "令牌格式错误"),
    // 无更新参数
    NO_UPDATE_PARAMETER(630001, "无更新参数"),
    // 用户ID为空
    USER_ID_NULL(640111, "用户ID为空"),
    // 用户不存在
    USER_NOT_EXIST(640115, "用户不存在"),
    // 用户被禁用
    USER_DISABLED(640118, "用户被禁用"),
    ;
    // 可根据实际业务添加其他业务异常

    private final int code;
    private final String message;

    BusinessExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
