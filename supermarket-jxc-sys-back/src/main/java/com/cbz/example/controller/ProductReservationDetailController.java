package com.cbz.example.controller;

import com.cbz.example.common.PageBo;
import com.cbz.example.entity.ProductReservationDetail;
import com.cbz.example.bo.ProductReservationDetailBo;
import com.cbz.example.entity.ProductReservationDetailQuery;
import com.cbz.example.service.ProductReservationDetailService;
import com.cbz.example.common.Result;
import com.cbz.example.common.PageResult;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:41:15
*/
@RestController
@RequestMapping("/ProductReservationDetail")
public class ProductReservationDetailController {

    @Autowired
    private ProductReservationDetailService ProductReservationDetailService;

    /**
     * 通过id获取元素
     */
    @PostMapping("/getById")
    public Result<ProductReservationDetail> get(@RequestBody ProductReservationDetailBo productReservationDetailBo) {
        ProductReservationDetail ProductReservationDetail = ProductReservationDetailService.getById(productReservationDetailBo.getId());
        return Result.success(ProductReservationDetail);
    }

    /**
     * 获取列表全部
     */
    @PostMapping("/list")
    public Result<List<ProductReservationDetail>> list(@RequestBody ProductReservationDetailQuery ProductReservationDetailQuery) {
        QueryWrapper<ProductReservationDetail> wrapper = new QueryWrapper<>();
        List<ProductReservationDetail> list = ProductReservationDetailService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 添加元素(无需主键)
     */
    @PostMapping("/add")
    public Result<Boolean> save(@RequestBody ProductReservationDetailBo ProductReservationDetailBo) {

        ProductReservationDetail ProductReservationDetail = new ProductReservationDetail();
        BeanUtils.copyProperties(ProductReservationDetailBo, ProductReservationDetail);
        ProductReservationDetail.setCreateTime(new Date());

        boolean result = ProductReservationDetailService.save(ProductReservationDetail);
        return Result.success(result);
    }

    /**
     * 修改元素(需要主键)
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody ProductReservationDetailBo ProductReservationDetailBo) {

        ProductReservationDetail ProductReservationDetail = new ProductReservationDetail();
        BeanUtils.copyProperties(ProductReservationDetailBo, ProductReservationDetail);

        boolean result = ProductReservationDetailService.saveOrUpdate(ProductReservationDetail);
        return Result.success(result);
    }

    /**
     * 删除元素
     */
    @PostMapping("/deleteById")
    public Result<Boolean> delete(@RequestBody ProductReservationDetailBo ProductReservationDetailBo) {
        boolean result = ProductReservationDetailService.removeById(ProductReservationDetailBo.getId());
        return Result.success(result);
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public PageResult<ProductReservationDetail> page(@RequestBody PageBo<ProductReservationDetail> pageBo) {

        ProductReservationDetail ProductReservationDetailQuery = pageBo.getParams();

        QueryWrapper<ProductReservationDetail> wrapper = new QueryWrapper<>();
        // wrapper.orderBy(true, false, "sort");

        IPage<ProductReservationDetail> iPage = ProductReservationDetailService.page(new Page<>(pageBo.getPageNum(), pageBo.getPageSize()), wrapper);

        return new PageResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }

}
