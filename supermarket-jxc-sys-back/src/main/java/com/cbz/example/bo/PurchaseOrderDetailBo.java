package com.cbz.example.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PurchaseOrderDetailBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购订单详情ID
     */
    @JsonProperty("Id")
    private Integer Id;

    /**
     * 采购订单ID
     */
    @JsonProperty("PurchaseOrderId")
    private Integer PurchaseOrderId;

    /**
     * 产品ID
     */
    @JsonProperty("ProductId")
    private Integer ProductId;

    /**
     * 产品数量
     */
    @JsonProperty("ProductQuantity")
    private Integer ProductQuantity;

    /**
     * 供应商ID
     */
    @JsonProperty("SupplierId")
    private Integer SupplierId;

    /**
     * 员工ID
     */
    @JsonProperty("EmployeeId")
    private Integer EmployeeId;

    /**
     * 采购价格
     */
    @JsonProperty("PurchasePrice")
    private BigDecimal PurchasePrice;
}
