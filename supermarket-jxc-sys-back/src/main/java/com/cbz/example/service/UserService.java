package com.cbz.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cbz.example.bo.UserBo;
import com.cbz.example.bo.UserLoginBo;
import com.cbz.example.common.PageBo;
import com.cbz.example.common.PageResult;
import com.cbz.example.common.Result;
import com.cbz.example.entity.User;
import com.cbz.example.vo.UserVo;
import com.cbz.example.vo.query.UserQuery;

/**
 * @author 11272
 */
public interface UserService extends IService<User> {
    Result<String> register(UserBo userBo);

    Result<String> login(UserLoginBo userLoginBo);

    Result<String> updateUser(UserBo userBo);

    UserVo getUser(Long userId);

    PageResult<UserVo> pageList(PageBo<UserQuery> userPageBo);
}
