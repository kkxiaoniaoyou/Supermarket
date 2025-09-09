package com.cbz.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbz.example.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:40:38
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
