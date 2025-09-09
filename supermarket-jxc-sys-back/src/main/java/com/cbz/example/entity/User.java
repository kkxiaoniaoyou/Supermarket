package com.cbz.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 11272
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
     * 角色ID
     */
    private Integer roleId;
}
