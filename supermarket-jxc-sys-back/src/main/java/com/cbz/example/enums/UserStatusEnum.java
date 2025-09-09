package com.cbz.example.enums;

/**
 * 用户状态枚举
 */
public enum UserStatusEnum {

    // 正常
    ACTIVE(0),
    // 禁用
    DISABLE(1);

    private final Integer value;

    // 构造函数
    UserStatusEnum(Integer value) {
        this.value = value;
    }

    // 获取枚举常量的值
    public Integer getValue() {
        return value;
    }
}
