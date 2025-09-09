package com.cbz.example.common.domain;

import lombok.*;

/**
 * 已经上传的文件信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UploadedFile {
    /**
     * 带后缀的完整文件名
     */
    private String fileName;

    /**
     * 不带后缀的文件名
     */
    private String name;

    /**
     * 文件后缀名  jpg  png
     */
    private String suffix;

    /**
     * 文件访问url地址
     */
    private String url;

    /**
     * 上传的文件的大小 单位：字节
     */
    private Long size;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 文件中间路径
     */
    private String path;
}
