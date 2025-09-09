package com.cbz.example.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class EmployeeBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    @JsonProperty("Id")
    private Integer id;

    /**
     * 员工姓名
     */
    @JsonProperty("Name")
    private String name;

    /**
     * 密码
     */
    @JsonProperty("Password")
    private String password;

    /**
     * 电子邮箱
     */
    @JsonProperty("Email")
    private String email;

    /**
     * 电话号码
     */
    @JsonProperty("PhoneNumber")
    private String phoneNumber;

    /**
     * 部门ID
     */
    @JsonProperty("DepartmentId")
    private Integer departmentId;

    /**
     * 员工类型
     */
    @JsonProperty("Type")
    private String type;

}
