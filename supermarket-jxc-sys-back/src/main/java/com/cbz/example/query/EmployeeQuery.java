package com.cbz.example.entity;

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
public class EmployeeQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 员工ID
    */
    private Integer Id;

    /**
    * 员工姓名
    */
    private String Name;

    /**
    * 密码
    */
    private String Password;

    /**
    * 电子邮箱
    */
    private String Email;

    /**
    * 电话号码
    */
    private String PhoneNumber;

    /**
    * 部门ID
    */
    private Integer DepartmentId;

    /**
    * 员工类型
    */
    private String Type;

}
