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
* 库存表
* @author chenbozhi
* @date 2024-5-13 3:41:05
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("Inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    @TableField("Id")
    private Integer Id;

    /**
     * 产品ID
     */
    @TableField("ProductId")
    private Integer ProductId;

    /**
     * 销售价格
     */
    @TableField("SalePrice")
    private BigDecimal SalePrice;

    /**
     * 库存数量
     */
    @TableField("StockQuantity")
    private Integer StockQuantity;

}
