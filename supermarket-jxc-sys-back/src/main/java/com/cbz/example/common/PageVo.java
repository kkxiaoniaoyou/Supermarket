package com.cbz.example.common;

/**
 * @author 11272
 * @date 2024/1/10 18:04
 **/
public class PageVo<T> {

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 其他查询参数
     */
    private T params;
}
