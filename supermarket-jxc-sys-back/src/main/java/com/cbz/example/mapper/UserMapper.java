package com.cbz.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbz.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // Additional custom queries can be added here
}

