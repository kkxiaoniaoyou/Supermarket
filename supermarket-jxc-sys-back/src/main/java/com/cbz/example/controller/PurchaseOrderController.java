package com.cbz.example.controller;

import com.cbz.example.common.PageBo;
import com.cbz.example.entity.PurchaseOrder;
import com.cbz.example.bo.PurchaseOrderBo;
import com.cbz.example.entity.PurchaseOrderQuery;
import com.cbz.example.service.PurchaseOrderService;
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
* @date 2024-5-13 3:41:18
*/
@RestController
@RequestMapping("/PurchaseOrder")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService PurchaseOrderService;

    /**
     * 通过id获取元素
     */
    @PostMapping("/getById")
    public Result<PurchaseOrder> get(@RequestBody PurchaseOrderBo PurchaseOrderBo) {
        PurchaseOrder PurchaseOrder = PurchaseOrderService.getById(PurchaseOrderBo.getId());
        return Result.success(PurchaseOrder);
    }

    /**
     * 获取列表全部
     */
    @PostMapping("/list")
    public Result<List<PurchaseOrder>> list(@RequestBody PurchaseOrderQuery PurchaseOrderQuery) {
        QueryWrapper<PurchaseOrder> wrapper = new QueryWrapper<>();
        List<PurchaseOrder> list = PurchaseOrderService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 添加元素(无需主键)
     */
    @PostMapping("/add")
    public Result<Boolean> save(@RequestBody PurchaseOrderBo PurchaseOrderBo) {

        PurchaseOrder PurchaseOrder = new PurchaseOrder();
        BeanUtils.copyProperties(PurchaseOrderBo, PurchaseOrder);

        boolean result = PurchaseOrderService.saveOrUpdate(PurchaseOrder);
        return Result.success(result);
    }

    /**
     * 修改元素(需要主键)
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody PurchaseOrderBo PurchaseOrderBo) {

        PurchaseOrder PurchaseOrder = new PurchaseOrder();
        BeanUtils.copyProperties(PurchaseOrderBo, PurchaseOrder);

        boolean result = PurchaseOrderService.saveOrUpdate(PurchaseOrder);
        return Result.success(result);
    }

    /**
     * 删除元素
     */
    @PostMapping("/deleteById")
    public Result<Boolean> delete(@RequestBody PurchaseOrderBo PurchaseOrderBo) {
        boolean result = PurchaseOrderService.removeById(PurchaseOrderBo.getId());
        return Result.success(result);
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public PageResult<PurchaseOrder> page(@RequestBody PageBo<PurchaseOrder> pageBo) {

        PurchaseOrder PurchaseOrderQuery = pageBo.getParams();

        QueryWrapper<PurchaseOrder> wrapper = new QueryWrapper<>();
        // wrapper.orderBy(true, false, "sort");

        IPage<PurchaseOrder> iPage = PurchaseOrderService.page(new Page<>(pageBo.getPageNum(), pageBo.getPageSize()), wrapper);

        return new PageResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }

}
