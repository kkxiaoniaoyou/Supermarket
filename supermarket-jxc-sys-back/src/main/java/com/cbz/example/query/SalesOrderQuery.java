package com.cbz.example.entity;

import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* 销售订单表
* @author chenbozhi
* @date 2024-5-13 3:41:25
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 销售订单ID
    */
    private Integer Id;

    /**
    * 产品ID
    */
    private Integer ProductId;

    /**
    * 销售数量
    */
    private Integer SalesQuantity;

    /**
    * 销售价格
    */
    private BigDecimal SalesPrice;

    /**
    * 员工ID
    */
    private Integer EmployeeId;

    /**
    * 是否缺货
    */
    private Boolean IsOutOfStock;

}
