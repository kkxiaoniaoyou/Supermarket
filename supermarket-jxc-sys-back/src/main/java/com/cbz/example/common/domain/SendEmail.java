package com.cbz.example.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 11272
 * @date 2023/8/18 11:56
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendEmail {

    /**
     * 要发送给谁，目标邮箱
     */
    private String sendTo;
}
