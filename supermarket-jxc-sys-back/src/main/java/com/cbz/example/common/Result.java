package com.cbz.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通用响应结果对象
 *
 * @param <T>
 * @author 11272
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String status;
    private String message;
    private T data;

    /**
     * 业务成功，无数据返回
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "success", "操作成功", null);
    }

    /**
     * 业务成功 有数据返回 并自定义消息
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, "success", message, data);
    }

    /**
     * 业务成功，有数据返回
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", "操作成功", data);
    }

    /**
     * 业务成功
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(200, "success", message, null);
    }

    /**
     * 业务不成功
     */
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, "fail", message, null);
    }

    /**
     * 业务不成功
     */
    public static <T> Result<T> fail(Integer code, String message, T data) {
        return new Result<>(code, "fail", message, data);
    }

    /**
     * 系统异常
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(500, "error", message, null);
    }

    /**
     * 系统异常
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, "error", message, null);
    }
}
