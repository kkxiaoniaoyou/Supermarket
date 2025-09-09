package com.cbz.example.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SalesOrderBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售订单ID
     */
    @JsonProperty("Id")
    private Integer Id;

    /**
     * 产品ID
     */
    @JsonProperty("ProductId")
    private Integer ProductId;

    /**
     * 销售数量
     */
    @JsonProperty("SalesQuantity")
    private Integer SalesQuantity;

    /**
     * 销售价格
     */
    @JsonProperty("SalesPrice")
    private BigDecimal SalesPrice;

    /**
     * 员工ID
     */
    @JsonProperty("EmployeeId")
    private Integer EmployeeId;

    /**
     * 是否缺货
     */
    @JsonProperty("IsOutOfStock")
    private Boolean IsOutOfStock;
}
