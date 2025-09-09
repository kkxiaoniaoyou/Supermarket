package com.cbz.example.vo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页条件查询 入参 用户查询
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery {

    /**
     * 状态：0-正常，1-禁用
     */
    private Integer status;

    /**
     * 角色 1-管理员 2-工作人员 3-业主
     */
    private Long roleId;
}
