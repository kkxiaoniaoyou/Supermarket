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
* 销售订单表
* @author chenbozhi
* @date 2024-5-13 3:41:25
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("SalesOrder")
public class SalesOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售订单ID
     */
    @TableField("Id")
    private Integer Id;

    /**
     * 产品ID
     */
    @TableField("ProductId")
    private Integer ProductId;

    /**
     * 销售数量
     */
    @TableField("SalesQuantity")
    private Integer SalesQuantity;

    /**
     * 销售价格
     */
    @TableField("SalesPrice")
    private BigDecimal SalesPrice;

    /**
     * 员工ID
     */
    @TableField("EmployeeId")
    private Integer EmployeeId;

    /**
     * 是否缺货
     */
    @TableField("IsOutOfStock")
    private Boolean IsOutOfStock;

}

