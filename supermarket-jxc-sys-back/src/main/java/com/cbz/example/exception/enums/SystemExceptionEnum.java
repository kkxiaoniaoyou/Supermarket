package com.cbz.example.exception.enums;

/**
 * 全局系统异常枚举
 *
 * @author 11272
 * @date 2024/1/10 18:18
 **/
public enum SystemExceptionEnum {

    // 系统异常
    INTERNAL_SERVER_ERROR(500, "系统异常"),
    // 网络异常
    NETWORK_ERROR(501, "网络异常"),
    // 资源未找到
    NOT_FOUND(404, "资源未找到"),
    // 请求参数校验失败
    REQUEST_PARAMS_CHECK_FAIL(6001, "请求参数校验失败"),
    // 请求参数绑定失败
    REQUEST_PARAMS_BIND_FAIL(6005, "请求参数校验失败"),
    // 未授权
    UNAUTHORIZED(401, "未授权"),
    // 请先登录
    AUTHENTICATION_FAILURE(403, "请先登录"),
    // 请求方法不支持
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    TOKEN_NOT_VALID(401,"token无效,请重新登录或联系管理员")
    ;

    private final int code;
    private final String message;

    SystemExceptionEnum(int code, String message) {
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

