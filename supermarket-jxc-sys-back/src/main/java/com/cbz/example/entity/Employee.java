package com.cbz.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
* 员工表
* @author chenbozhi
* @date 2024-5-13 3:40:38
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("Employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    @TableField("Id")
    private Integer Id;

    /**
     * 员工姓名
     */
    @TableField("Name")
    private String Name;

    /**
     * 密码
     */
    @TableField("Password")
    private String Password;

    /**
     * 电子邮箱
     */
    @TableField("Email")
    private String Email;

    /**
     * 电话号码
     */
    @TableField("PhoneNumber")
    private String PhoneNumber;

    /**
     * 部门ID
     */
    @TableField("DepartmentId")
    private Integer DepartmentId;

    /**
     * 员工类型
     */
    @TableField("Type")
    private String Type;

}

