package com.cbz.example.controller;

import com.cbz.example.common.PageBo;
import com.cbz.example.entity.Inventory;
import com.cbz.example.bo.InventoryBo;
import com.cbz.example.entity.InventoryQuery;
import com.cbz.example.service.InventoryService;
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
* @date 2024-5-13 3:41:05
*/
@RestController
@RequestMapping("/Inventory")
public class InventoryController {

    @Autowired
    private InventoryService InventoryService;

    /**
     * 通过id获取元素
     */
    @PostMapping("/getById")
    public Result<Inventory> get(@RequestBody InventoryBo InventoryBo) {
        Inventory Inventory = InventoryService.getById(InventoryBo.getId());
        return Result.success(Inventory);
    }

    /**
     * 获取列表全部
     */
    @PostMapping("/list")
    public Result<List<Inventory>> list(@RequestBody InventoryQuery InventoryQuery) {
        QueryWrapper<Inventory> wrapper = new QueryWrapper<>();
        List<Inventory> list = InventoryService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 添加元素(无需主键)
     */
    @PostMapping("/add")
    public Result<Boolean> save(@RequestBody InventoryBo InventoryBo) {

        Inventory Inventory = new Inventory();
        BeanUtils.copyProperties(InventoryBo, Inventory);

        boolean result = InventoryService.saveOrUpdate(Inventory);
        return Result.success(result);
    }

    /**
     * 修改元素(需要主键)
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody InventoryBo InventoryBo) {

        Inventory Inventory = new Inventory();
        BeanUtils.copyProperties(InventoryBo, Inventory);

        boolean result = InventoryService.saveOrUpdate(Inventory);
        return Result.success(result);
    }

    /**
     * 删除元素
     */
    @PostMapping("/deleteById")
    public Result<Boolean> delete(@RequestBody InventoryBo InventoryBo) {
        boolean result = InventoryService.removeById(InventoryBo.getId());
        return Result.success(result);
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public PageResult<Inventory> page(@RequestBody PageBo<Inventory> pageBo) {

        Inventory InventoryQuery = pageBo.getParams();

        QueryWrapper<Inventory> wrapper = new QueryWrapper<>();
        // wrapper.orderBy(true, false, "sort");

        IPage<Inventory> iPage = InventoryService.page(new Page<>(pageBo.getPageNum(), pageBo.getPageSize()), wrapper);

        return new PageResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }

}
