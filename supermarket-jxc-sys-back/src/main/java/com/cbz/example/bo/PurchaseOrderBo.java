package com.cbz.example.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PurchaseOrderBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购订单ID
     */
    @JsonProperty("Id")
    private Integer Id;

    /**
     * 订单名称
     */
    @JsonProperty("Name")
    private String Name;

    /**
     * 时间
     * 注解@JsonFormat:从数据库读出日期格式时,进行转换的规则
     * 注解@DateTimeFormat:接受从前端传入的日期格式,映射到java类日期属性的规则
     */
    @JsonProperty("Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date Time;

    /**
     * 总价格
     */
    @JsonProperty("TotalPrice")
    private BigDecimal TotalPrice;

    /**
     * 是否入库
     */
    @JsonProperty("IsInStock")
    private Boolean IsInStock;


}
