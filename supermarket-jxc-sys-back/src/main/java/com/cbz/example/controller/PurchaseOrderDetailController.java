package com.cbz.example.controller;

import com.cbz.example.common.PageBo;
import com.cbz.example.entity.PurchaseOrderDetail;
import com.cbz.example.bo.PurchaseOrderDetailBo;
import com.cbz.example.entity.PurchaseOrderDetailQuery;
import com.cbz.example.service.PurchaseOrderDetailService;
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
* @date 2024-5-13 3:41:22
*/
@RestController
@RequestMapping("/PurchaseOrderDetail")
public class PurchaseOrderDetailController {

    @Autowired
    private PurchaseOrderDetailService PurchaseOrderDetailService;

    /**
     * 通过id获取元素
     */
    @PostMapping("/getById")
    public Result<PurchaseOrderDetail> get(@RequestBody PurchaseOrderDetailBo PurchaseOrderDetailBo) {
        PurchaseOrderDetail PurchaseOrderDetail = PurchaseOrderDetailService.getById(PurchaseOrderDetailBo.getId());
        return Result.success(PurchaseOrderDetail);
    }

    /**
     * 获取列表全部
     */
    @PostMapping("/list")
    public Result<List<PurchaseOrderDetail>> list(@RequestBody PurchaseOrderDetailQuery PurchaseOrderDetailQuery) {
        QueryWrapper<PurchaseOrderDetail> wrapper = new QueryWrapper<>();
        List<PurchaseOrderDetail> list = PurchaseOrderDetailService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 添加元素(无需主键)
     */
    @PostMapping("/add")
    public Result<Boolean> save(@RequestBody PurchaseOrderDetailBo PurchaseOrderDetailBo) {

        PurchaseOrderDetail PurchaseOrderDetail = new PurchaseOrderDetail();
        BeanUtils.copyProperties(PurchaseOrderDetailBo, PurchaseOrderDetail);

        boolean result = PurchaseOrderDetailService.saveOrUpdate(PurchaseOrderDetail);
        return Result.success(result);
    }

    /**
     * 修改元素(需要主键)
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody PurchaseOrderDetailBo PurchaseOrderDetailBo) {

        PurchaseOrderDetail PurchaseOrderDetail = new PurchaseOrderDetail();
        BeanUtils.copyProperties(PurchaseOrderDetailBo, PurchaseOrderDetail);

        boolean result = PurchaseOrderDetailService.saveOrUpdate(PurchaseOrderDetail);
        return Result.success(result);
    }

    /**
     * 删除元素
     */
    @PostMapping("/deleteById")
    public Result<Boolean> delete(@RequestBody PurchaseOrderDetailBo PurchaseOrderDetailBo) {
        boolean result = PurchaseOrderDetailService.removeById(PurchaseOrderDetailBo.getId());
        return Result.success(result);
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public PageResult<PurchaseOrderDetail> page(@RequestBody PageBo<PurchaseOrderDetail> pageBo) {

        PurchaseOrderDetail PurchaseOrderDetailQuery = pageBo.getParams();

        QueryWrapper<PurchaseOrderDetail> wrapper = new QueryWrapper<>();
        // wrapper.orderBy(true, false, "sort");

        IPage<PurchaseOrderDetail> iPage = PurchaseOrderDetailService.page(new Page<>(pageBo.getPageNum(), pageBo.getPageSize()), wrapper);

        return new PageResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }

}
