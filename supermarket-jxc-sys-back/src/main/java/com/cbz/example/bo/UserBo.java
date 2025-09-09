package com.cbz.example.bo;

import com.cbz.example.common.groupValidation.AddGroup;
import com.cbz.example.common.groupValidation.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBo {

    /**
     * 用户ID
     */
    @Null(message = "用户ID在添加时应为空", groups = {AddGroup.class})
    @NotNull(message = "用户ID不能为空", groups = {UpdateGroup.class})
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "用户名只能包含字母和数字", groups = {AddGroup.class, UpdateGroup.class})
    @Size(min = 6, max = 16, message = "用户名长度必须在{min}到{max}之间", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "密码只能包含字母和数字", groups = {AddGroup.class, UpdateGroup.class})
    @Size(min = 8, max = 18, message = "密码长度必须在{min}到{max}之间", groups = {AddGroup.class, UpdateGroup.class})
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class})
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号不能为空", groups = {AddGroup.class})
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String phoneNumber;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空", groups = {AddGroup.class})
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
     * 状态：1-正常，0-禁用
     */
    private Integer status;

    /**
     * 角色ID
     */
    private Integer roleId;
}
