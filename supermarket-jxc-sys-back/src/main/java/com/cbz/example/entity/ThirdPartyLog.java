package com.cbz.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 第三方接口调用记录
 *
 * @TableName sys_third_party_log
 */
@TableName(value = "sys_third_party_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThirdPartyLog implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 请求类型（GET/POST等）
     */
    private String requestType;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应结果
     */
    private String responseResult;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 响应时间
     */
    private Date responseTime;

    /**
     * 请求耗时（毫秒）
     */
    private Integer requestDuration;

    /**
     * 响应状态码
     */
    private Integer responseStatus;

    /**
     * 请求关键字
     */
    private String requestKeyword;

    /**
     * 请求主机地址
     */
    private String requestHost;

    /**
     * 请求是否成功
     */
    private Integer isSuccess;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
