package com.cbz.example.controller;

import com.cbz.example.common.PageBo;
import com.cbz.example.entity.Employee;
import com.cbz.example.bo.EmployeeBo;
import com.cbz.example.entity.EmployeeQuery;
import com.cbz.example.service.EmployeeService;
import com.cbz.example.common.Result;
import com.cbz.example.common.PageResult;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:40:38
*/
@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService EmployeeService;

    /**
     * 通过id获取元素
     */
    @PostMapping("/getById")
    public Result<Employee> get(@RequestBody EmployeeBo EmployeeBo) {
        Employee Employee = EmployeeService.getById(EmployeeBo.getId());
        return Result.success(Employee);
    }

    /**
     * 获取列表全部
     */
    @PostMapping("/list")
    public Result<List<Employee>> list(@RequestBody EmployeeQuery EmployeeQuery) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        List<Employee> list = EmployeeService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 添加元素(无需主键)
     */
    @PostMapping("/add")
    public Result<Boolean> save(@RequestBody EmployeeBo EmployeeBo) {

        Employee Employee = new Employee();
        BeanUtils.copyProperties(EmployeeBo, Employee);

        boolean result = EmployeeService.saveOrUpdate(Employee);
        return Result.success(result);
    }

    /**
     * 修改元素(需要主键)
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody EmployeeBo EmployeeBo) {

        Employee Employee = new Employee();
        BeanUtils.copyProperties(EmployeeBo, Employee);

        boolean result = EmployeeService.saveOrUpdate(Employee);
        return Result.success(result);
    }

    /**
     * 删除元素
     */
    @PostMapping("/deleteById")
    public Result<Boolean> delete(@RequestBody EmployeeBo EmployeeBo) {
        boolean result = EmployeeService.removeById(EmployeeBo.getId());
        return Result.success(result);
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public PageResult<Employee> page(@RequestBody PageBo<Employee> pageBo) {

        Employee EmployeeQuery = pageBo.getParams();

        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        // wrapper.orderBy(true, false, "sort");

        IPage<Employee> iPage = EmployeeService.page(new Page<>(pageBo.getPageNum(), pageBo.getPageSize()), wrapper);

        return new PageResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }

}
