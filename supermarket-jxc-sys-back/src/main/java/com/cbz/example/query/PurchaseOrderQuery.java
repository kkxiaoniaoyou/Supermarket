package com.cbz.example.entity;

import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 采购订单表
* @author chenbozhi
* @date 2024-5-13 3:41:18
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 采购订单ID
    */
    private Integer Id;

    /**
    * 订单名称
    */
    private String Name;

    /**
    * 时间
    * 注解@JsonFormat:从数据库读出日期格式时,进行转换的规则
    * 注解@DateTimeFormat:接受从前端传入的日期格式,映射到java类日期属性的规则
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date Time;

    /**
    * 总价格
    */
    private BigDecimal TotalPrice;

    /**
    * 是否入库
    */
    private Boolean IsInStock;

}
