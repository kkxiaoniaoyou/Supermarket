package com.cbz.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* 采购订单详情表
* @author chenbozhi
* @date 2024-5-13 3:41:21
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("PurchaseOrderDetail")
public class PurchaseOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购订单详情ID
     */
    @TableField("Id")
    private Integer Id;

    /**
     * 采购订单ID
     */
    @TableField("PurchaseOrderId")
    private Integer PurchaseOrderId;

    /**
     * 产品ID
     */
    @TableField("ProductId")
    private Integer ProductId;

    /**
     * 产品数量
     */
    @TableField("ProductQuantity")
    private Integer ProductQuantity;

    /**
     * 供应商ID
     */
    @TableField("SupplierId")
    private Integer SupplierId;

    /**
     * 员工ID
     */
    @TableField("EmployeeId")
    private Integer EmployeeId;

    /**
     * 采购价格
     */
    @TableField("PurchasePrice")
    private BigDecimal PurchasePrice;

}
