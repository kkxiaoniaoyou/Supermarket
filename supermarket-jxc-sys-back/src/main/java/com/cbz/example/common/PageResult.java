package com.cbz.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 11272
 * @description: TODO
 * @date 2024/1/10 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    /**
     * 当前页
     */
    private Long pageNum;

    /**
     * 页面大小
     */
    private Long pageSize;

    /**
     * 总数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 页面信息
     */
    private List<T> list;
}
