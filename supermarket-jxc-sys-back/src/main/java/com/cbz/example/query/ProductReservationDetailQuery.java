package com.cbz.example.entity;

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
public class ProductReservationDetailQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 产品预定ID
    */
    private Integer ProductReservationId;

    /**
    * 顾客ID
    */
    private Integer CustomerId;

    /**
    * 产品名称
    */
    private String ProductName;

    /**
    * 预定数量
    */
    private Integer ReservationQuantity;

    /**
    * 供应商ID
    */
    private Integer SupplierId;

}
