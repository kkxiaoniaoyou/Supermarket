package com.cbz.example.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class InventoryBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    @JsonProperty("Id")
    private Integer id;

    /**
     * 产品ID
     */
    @JsonProperty("ProductId")
    private Integer productId;

    /**
     * 销售价格
     */
    @JsonProperty("SalePrice")
    private BigDecimal salePrice;

    /**
     * 库存数量
     */
    @JsonProperty("StockQuantity")
    private Integer stockQuantity;

}
