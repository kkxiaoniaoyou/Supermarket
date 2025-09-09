package com.cbz.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbz.example.entity.Employee;
import com.cbz.example.mapper.EmployeeMapper;
import com.cbz.example.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:40:38
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
