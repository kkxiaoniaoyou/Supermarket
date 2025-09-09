package com.cbz.example.entity;

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
public class PurchaseOrderDetailQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 采购订单详情ID
    */
    private Integer Id;

    /**
    * 采购订单ID
    */
    private Integer PurchaseOrderId;

    /**
    * 产品ID
    */
    private Integer ProductId;

    /**
    * 产品数量
    */
    private Integer ProductQuantity;

    /**
    * 供应商ID
    */
    private Integer SupplierId;

    /**
    * 员工ID
    */
    private Integer EmployeeId;

    /**
    * 采购价格
    */
    private BigDecimal PurchasePrice;

}
