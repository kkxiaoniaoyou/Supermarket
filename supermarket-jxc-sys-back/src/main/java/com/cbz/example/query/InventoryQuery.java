package com.cbz.example.entity;

import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* 库存表
* @author chenbozhi
* @date 2024-5-13 3:41:05
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InventoryQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 库存ID
    */
    private Integer Id;

    /**
    * 产品ID
    */
    private Integer ProductId;

    /**
    * 销售价格
    */
    private BigDecimal SalePrice;

    /**
    * 库存数量
    */
    private Integer StockQuantity;

}
