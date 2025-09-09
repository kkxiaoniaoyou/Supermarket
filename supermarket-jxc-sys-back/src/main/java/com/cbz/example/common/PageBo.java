package com.cbz.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 11272
 * @date 2024/1/10 18:04
 **/
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PageBo<T> {

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
