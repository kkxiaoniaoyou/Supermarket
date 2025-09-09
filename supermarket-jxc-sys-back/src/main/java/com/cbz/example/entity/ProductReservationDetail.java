package com.cbz.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
* 产品预定详情表
* @author chenbozhi
* @date 2024-5-13 3:41:15
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("ProductReservationDetail")
public class ProductReservationDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableField("Id")
    private Integer Id;

    /**
     * 产品预定ID
     */
    @TableField("ProductReservationId")
    private Integer ProductReservationId;

    /**
     * 顾客ID
     */
    @TableField("CustomerId")
    private Integer CustomerId;

    /**
     * 产品名称
     */
    @TableField("ProductName")
    private String ProductName;

    /**
     * 预定数量
     */
    @TableField("ReservationQuantity")
    private Integer ReservationQuantity;

    /**
     * 供应商ID
     */
    @TableField("SupplierId")
    private Integer SupplierId;

    @TableField("CreateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date CreateTime;
}

