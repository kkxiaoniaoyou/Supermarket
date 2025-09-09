package com.cbz.example.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
* 产品预定详情表
* @author chenbozhi
* @date 2024-5-13 3:41:15
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductReservationDetailBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @JsonProperty(value = "Id")
    private Integer Id;


    /**
     * 产品预定ID
     */
    @JsonProperty("ProductReservationId")
    private Integer ProductReservationId;

    /**
     * 顾客ID
     */
    @JsonProperty("CustomerId")
    private Integer CustomerId;

    /**
     * 产品名称
     */
    @JsonProperty("ProductName")
    private String ProductName;

    /**
     * 预定数量
     */
    @JsonProperty("ReservationQuantity")
    private Integer ReservationQuantity;

    /**
     * 供应商ID
     */
    @JsonProperty("SupplierId")
    private Integer SupplierId;
}
