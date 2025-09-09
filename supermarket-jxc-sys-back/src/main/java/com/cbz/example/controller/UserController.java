package com.cbz.example.controller;

import com.cbz.example.bo.UserLoginBo;
import com.cbz.example.bo.UserBo;
import com.cbz.example.common.PageBo;
import com.cbz.example.common.PageResult;
import com.cbz.example.common.groupValidation.AddGroup;
import com.cbz.example.common.groupValidation.UpdateGroup;
import com.cbz.example.entity.User;
import com.cbz.example.service.UserService;
import com.cbz.example.common.Result;
import com.cbz.example.vo.UserVo;
import com.cbz.example.vo.query.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 11272
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Result<String> login(@Valid @RequestBody UserLoginBo userLoginBo) {
        return userService.login(userLoginBo);
    }

    @PostMapping("/register")
    public Result<String> register(@Validated({AddGroup.class}) @RequestBody UserBo userBo) {
        return userService.register(userBo);
    }

    @PostMapping("/update")
    public Result<String> update(@Validated({UpdateGroup.class}) @RequestBody UserBo userBo) {
        return userService.updateUser(userBo);
    }

    @PostMapping("/pageList")
    public Result<PageResult<UserVo>> pageList(@RequestBody PageBo<UserQuery> userPageBo) {
        return Result.success(userService.pageList(userPageBo));
    }

    @GetMapping("/list")
    public Result<List<User>> getAllUsers() {
        List<User> userList = userService.list();
        return Result.success(userList);
    }

    @GetMapping("/{userId}")
    public Result<UserVo> getUser(@PathVariable("userId") Long userId) {
        return Result.success(userService.getUser(userId));
    }

    @DeleteMapping("/{userId}")
    public Result<Boolean> delete(@PathVariable("userId") Long userId) {
        return Result.success(userService.removeById(userId));
    }
}
