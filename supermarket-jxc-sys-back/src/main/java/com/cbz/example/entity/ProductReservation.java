package com.cbz.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
* 产品预定表
* @author chenbozhi
* @date 2024-5-13 3:41:12
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("ProductReservation")
public class ProductReservation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 预定ID
     */
    @TableField("Id")
    private Integer Id;

    /**
     * 顾客ID
     */
    @TableField("CustomerId")
    private Integer CustomerId;

    /**
     * 预定名称
     */
    @TableField("Name")
    private String Name;

    /**
     * 状态
     */
    @TableField("Status")
    private String Status;

    /**
     * 员工ID
     */
    @TableField("EmployeeId")
    private Integer EmployeeId;

    /**
     * 创建时间
     * 注解@JsonFormat:从数据库读出日期格式时,进行转换的规则
     * 注解@DateTimeFormat:接受从前端传入的日期格式,映射到java类日期属性的规则
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("CreatedAt")
    private Date CreatedAt;

}

