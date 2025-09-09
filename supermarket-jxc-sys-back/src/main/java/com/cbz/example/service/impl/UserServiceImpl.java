package com.cbz.example.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbz.example.bo.UserBo;
import com.cbz.example.bo.UserLoginBo;
import com.cbz.example.common.PageBo;
import com.cbz.example.common.PageResult;
import com.cbz.example.common.Result;
import com.cbz.example.entity.User;
import com.cbz.example.enums.UserStatusEnum;
import com.cbz.example.exception.BusinessException;
import com.cbz.example.exception.enums.BusinessExceptionEnum;
import com.cbz.example.interceptor.JwtTokenProvider;
import com.cbz.example.mapper.UserMapper;
import com.cbz.example.service.UserService;
import com.cbz.example.utils.MyBeanUtil;
import com.cbz.example.vo.UserVo;
import com.cbz.example.vo.query.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 11272
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${captcha.redis.key-prefix}")
    private String keyPrefix;

    private final RedisTemplate<String, Object> redisTemplate;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Result<String> register(UserBo userBo) {
        // 判断用户名是否已存在
        multipleUsernameCheck(userBo);
        // 判断手机号是否已存在
        multiplePhoneCheck(userBo);
        // 判断邮箱是否已存在
        multipleEmailCheck(userBo);

        User user = new User();
        BeanUtils.copyProperties(userBo, user);

        // 密码加密
        String password = userBo.getPassword();
        user.setPassword(BCrypt.hashpw(password));

        this.baseMapper.insert(user);
        return Result.success("注册成功!");
    }

    @Override
    public Result<String> login(UserLoginBo userLoginBo) {
        // 验证验证码
        String captcha = (String) redisTemplate.opsForValue().get(keyPrefix);
        if (!userLoginBo.getCaptcha().equals(captcha)) {
            return Result.fail(BusinessExceptionEnum.CAPTCHA_UN_MATCH.getCode(), BusinessExceptionEnum.CAPTCHA_UN_MATCH.getMessage());
        }
        // 可以删掉redis中的验证码
        redisTemplate.delete(keyPrefix);

        // 验证用户名是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userLoginBo.getUsername());
        queryWrapper.eq("role_id", userLoginBo.getRoleId());
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            return Result.fail(BusinessExceptionEnum.USER_NOT_EXIST.getCode(), BusinessExceptionEnum.USER_NOT_EXIST.getMessage());
        }

        // 验证用户是否禁用
        if(user.getStatus().equals(UserStatusEnum.DISABLE.getValue())) {
            return Result.fail(BusinessExceptionEnum.USER_DISABLED.getCode(), BusinessExceptionEnum.USER_DISABLED.getMessage());
        }

        // 验证密码是否正确
        if (!BCrypt.checkpw(userLoginBo.getPassword(), user.getPassword())) {
            return Result.fail(BusinessExceptionEnum.USERNAME_PASSWORD_UN_MATCH.getCode(), BusinessExceptionEnum.USERNAME_PASSWORD_UN_MATCH.getMessage());
        }
        // 登录成功
        user.setPassword(null);
        String token = jwtTokenProvider.generateToken((JSONUtil.toJsonStr(user)));

        // 更新登录时间
        updateLastLoginTime(user);

        return Result.success("登录成功!", token);
    }

    @Override
    public Result<String> updateUser(UserBo userBo) {
        User user = new User();
        BeanUtils.copyProperties(userBo, user);

        // 能够进到这里来说明userId不为空,但是只有userId一个不为空的话,update语句会有问题,也就是缺少set部分
        // 所以这里判断是否user非空字段数量大于1
        if (MyBeanUtil.countNonNullProperties(user) <= 1) {
            return Result.fail(BusinessExceptionEnum.NO_UPDATE_PARAMETER.getCode(), BusinessExceptionEnum.NO_UPDATE_PARAMETER.getMessage());
        }
        // 更新也需要做用户名、手机号、邮箱的唯一性校验
        // 通过id获取当前用户
        User realUser = this.baseMapper.selectById(user.getUserId());

        // 唯一性校验，只有在目前的用户名和数据库中的用户名不一致时才需要校验
        if (!realUser.getUsername().equals(userBo.getUsername())) {
            multipleUsernameCheck(userBo);
        }
        // 手机号也需要做唯一性校验
        if (realUser.getPhoneNumber() != null && !realUser.getPhoneNumber().equals(userBo.getPhoneNumber())) {
            multiplePhoneCheck(userBo);
        }
        // 邮箱也需要做唯一性校验
        if (realUser.getEmail() != null && !realUser.getEmail().equals(userBo.getEmail())) {
            multipleEmailCheck(userBo);
        }

        // 如果密码不为空,则需要对密码进行加密
        if (userBo.getPassword() != null) {
            String password = userBo.getPassword();
            user.setPassword(BCrypt.hashpw(password));
        }

        this.baseMapper.updateById(user);
        return Result.success("更新成功!");
    }

    @Override
    public UserVo getUser(Long userId) {
        if (userId == null) {
            throw new BusinessException(BusinessExceptionEnum.USER_ID_NULL.getCode(), BusinessExceptionEnum.USER_ID_NULL.getMessage());
        }
        User user = this.baseMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(BusinessExceptionEnum.USER_NOT_EXIST.getCode(), BusinessExceptionEnum.USER_NOT_EXIST.getMessage());
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public PageResult<UserVo> pageList(PageBo<UserQuery> userPageBo) {
        // 创建分页对象
        Page<User> page = new Page<>(userPageBo.getPageNum(), userPageBo.getPageSize());

        UserQuery userQuery = userPageBo.getParams();

        // 创建条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq(userQuery.getRoleId() != null, "role_id", userQuery.getRoleId());
        wrapper.eq(userQuery.getStatus() != null, "`status`", userQuery.getStatus());

/*        // 设置条件，可以根据实际情况设置多个条件
        if (condition.getUsername() != null) {
            wrapper.like("username", condition.getUsername());
        }
        if (condition.getAge() != null) {
            wrapper.eq("age", condition.getAge());
        }*/

        wrapper.orderBy(true, false, "registration_time");

        Page<User> userPage = this.baseMapper.selectPage(page, wrapper);

        List<User> records = userPage.getRecords();
        List<UserVo> userVoList = new ArrayList<>();
        for (User user : records) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVoList.add(userVo);
        }

        return new PageResult<UserVo>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal(), userPage.getPages(), userVoList);
    }

    /**
     * 用户名重复检测
     *
     * @param userBo
     * @return
     */
    private void multipleUsernameCheck(UserBo userBo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userBo.getUsername());
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user != null) {
            throw new BusinessException(BusinessExceptionEnum.USERNAME_EXIST.getCode(), BusinessExceptionEnum.USERNAME_EXIST.getMessage());
        }
    }

    /**
     * 手机号重复检测
     *
     * @param userBo
     * @return
     */
    private void multiplePhoneCheck(UserBo userBo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number", userBo.getPhoneNumber());
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user != null) {
            throw new BusinessException(BusinessExceptionEnum.PHONE_EXIST.getCode(), BusinessExceptionEnum.PHONE_EXIST.getMessage());
        }
    }

    /**
     * 手机号重复检测
     *
     * @param userBo
     * @return
     */
    private void multipleEmailCheck(UserBo userBo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userBo.getEmail());
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user != null) {
            throw new BusinessException(BusinessExceptionEnum.EMAIL_EXIST.getCode(), BusinessExceptionEnum.EMAIL_EXIST.getMessage());
        }
    }

    /**
     * 更新登录时间
     *
     * @param user
     */
    private void updateLastLoginTime(User user) {
        User user1 = new User();
        user1.setUserId(user.getUserId());
        user1.setLastLoginTime(new Date());
        this.baseMapper.updateById(user1);
    }
}

