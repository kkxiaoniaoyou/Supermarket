package com.cbz.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 个人介绍
     */
    private String introduction;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 注册时间
     */
    private Date registrationTime;

    /**
     * 状态：1-正常，0-禁用
     */
    private Integer status;

    /**
     * 角色id
     */
    private Integer roleId;
}
