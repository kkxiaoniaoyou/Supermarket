package com.cbz.example.controller;

import com.cbz.example.common.PageBo;
import com.cbz.example.entity.ProductReservation;
import com.cbz.example.bo.ProductReservationBo;
import com.cbz.example.entity.ProductReservationQuery;
import com.cbz.example.service.ProductReservationService;
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
* @date 2024-5-13 3:41:12
*/
@RestController
@RequestMapping("/ProductReservation")
public class ProductReservationController {

    @Autowired
    private ProductReservationService ProductReservationService;

    /**
     * 通过id获取元素
     */
    @PostMapping("/getById")
    public Result<ProductReservation> get(@RequestBody ProductReservationBo ProductReservationBo) {
        ProductReservation ProductReservation = ProductReservationService.getById(ProductReservationBo.getId());
        return Result.success(ProductReservation);
    }

    /**
     * 获取列表全部
     */
    @PostMapping("/list")
    public Result<List<ProductReservation>> list(@RequestBody ProductReservationQuery ProductReservationQuery) {
        QueryWrapper<ProductReservation> wrapper = new QueryWrapper<>();
        List<ProductReservation> list = ProductReservationService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 添加元素(无需主键)
     */
    @PostMapping("/add")
    public Result<Boolean> save(@RequestBody ProductReservationBo ProductReservationBo) {

        ProductReservation ProductReservation = new ProductReservation();
        BeanUtils.copyProperties(ProductReservationBo, ProductReservation);

        boolean result = ProductReservationService.saveOrUpdate(ProductReservation);
        return Result.success(result);
    }

    /**
     * 修改元素(需要主键)
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody ProductReservationBo ProductReservationBo) {

        ProductReservation ProductReservation = new ProductReservation();
        BeanUtils.copyProperties(ProductReservationBo, ProductReservation);

        boolean result = ProductReservationService.saveOrUpdate(ProductReservation);
        return Result.success(result);
    }

    /**
     * 删除元素
     */
    @PostMapping("/deleteById")
    public Result<Boolean> delete(@RequestBody ProductReservationBo ProductReservationBo) {
        boolean result = ProductReservationService.removeById(ProductReservationBo.getId());
        return Result.success(result);
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public PageResult<ProductReservation> page(@RequestBody PageBo<ProductReservation> pageBo) {

        ProductReservation ProductReservationQuery = pageBo.getParams();

        QueryWrapper<ProductReservation> wrapper = new QueryWrapper<>();
        // wrapper.orderBy(true, false, "sort");

        IPage<ProductReservation> iPage = ProductReservationService.page(new Page<>(pageBo.getPageNum(), pageBo.getPageSize()), wrapper);

        return new PageResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }

}
