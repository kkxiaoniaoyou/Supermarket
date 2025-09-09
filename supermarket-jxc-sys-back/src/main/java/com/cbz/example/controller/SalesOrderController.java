package com.cbz.example.controller;

import com.cbz.example.common.PageBo;
import com.cbz.example.entity.SalesOrder;
import com.cbz.example.bo.SalesOrderBo;
import com.cbz.example.entity.SalesOrderQuery;
import com.cbz.example.service.SalesOrderService;
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
* @date 2024-5-13 3:41:25
*/
@RestController
@RequestMapping("/SalesOrder")
public class SalesOrderController {

    @Autowired
    private SalesOrderService SalesOrderService;

    /**
     * 通过id获取元素
     */
    @PostMapping("/getById")
    public Result<SalesOrder> get(@RequestBody SalesOrderBo SalesOrderBo) {
        SalesOrder SalesOrder = SalesOrderService.getById(SalesOrderBo.getId());
        return Result.success(SalesOrder);
    }

    /**
     * 获取列表全部
     */
    @PostMapping("/list")
    public Result<List<SalesOrder>> list(@RequestBody SalesOrderQuery SalesOrderQuery) {
        QueryWrapper<SalesOrder> wrapper = new QueryWrapper<>();
        List<SalesOrder> list = SalesOrderService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 添加元素(无需主键)
     */
    @PostMapping("/add")
    public Result<Boolean> save(@RequestBody SalesOrderBo SalesOrderBo) {

        SalesOrder SalesOrder = new SalesOrder();
        BeanUtils.copyProperties(SalesOrderBo, SalesOrder);

        boolean result = SalesOrderService.saveOrUpdate(SalesOrder);
        return Result.success(result);
    }

    /**
     * 修改元素(需要主键)
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SalesOrderBo SalesOrderBo) {

        SalesOrder SalesOrder = new SalesOrder();
        BeanUtils.copyProperties(SalesOrderBo, SalesOrder);

        boolean result = SalesOrderService.saveOrUpdate(SalesOrder);
        return Result.success(result);
    }

    /**
     * 删除元素
     */
    @PostMapping("/deleteById")
    public Result<Boolean> delete(@RequestBody SalesOrderBo SalesOrderBo) {
        boolean result = SalesOrderService.removeById(SalesOrderBo.getId());
        return Result.success(result);
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public PageResult<SalesOrder> page(@RequestBody PageBo<SalesOrder> pageBo) {

        SalesOrder SalesOrderQuery = pageBo.getParams();

        QueryWrapper<SalesOrder> wrapper = new QueryWrapper<>();
        // wrapper.orderBy(true, false, "sort");

        IPage<SalesOrder> iPage = SalesOrderService.page(new Page<>(pageBo.getPageNum(), pageBo.getPageSize()), wrapper);

        return new PageResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }

}
