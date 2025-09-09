package com.cbz.example.controller;

import com.cbz.example.common.PageBo;
import com.cbz.example.entity.Product;
import com.cbz.example.bo.ProductBo;
import com.cbz.example.entity.ProductQuery;
import com.cbz.example.service.ProductService;
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
* @date 2024-5-13 3:41:09
*/
@RestController
@RequestMapping("/Product")
public class ProductController {

    @Autowired
    private ProductService ProductService;

    /**
     * 通过id获取元素
     */
    @PostMapping("/getById")
    public Result<Product> get(@RequestBody ProductBo ProductBo) {
        Product Product = ProductService.getById(ProductBo.getId());
        return Result.success(Product);
    }

    /**
     * 获取列表全部
     */
    @PostMapping("/list")
    public Result<List<Product>> list(@RequestBody ProductQuery ProductQuery) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        List<Product> list = ProductService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 添加元素(无需主键)
     */
    @PostMapping("/add")
    public Result<Boolean> save(@RequestBody ProductBo ProductBo) {

        Product Product = new Product();
        BeanUtils.copyProperties(ProductBo, Product);

        boolean result = ProductService.saveOrUpdate(Product);
        return Result.success(result);
    }

    /**
     * 修改元素(需要主键)
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody ProductBo ProductBo) {

        Product Product = new Product();
        BeanUtils.copyProperties(ProductBo, Product);

        boolean result = ProductService.saveOrUpdate(Product);
        return Result.success(result);
    }

    /**
     * 删除元素
     */
    @PostMapping("/deleteById")
    public Result<Boolean> delete(@RequestBody ProductBo ProductBo) {
        boolean result = ProductService.removeById(ProductBo.getId());
        return Result.success(result);
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public PageResult<Product> page(@RequestBody PageBo<Product> pageBo) {

        Product ProductQuery = pageBo.getParams();

        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        // wrapper.orderBy(true, false, "sort");

        IPage<Product> iPage = ProductService.page(new Page<>(pageBo.getPageNum(), pageBo.getPageSize()), wrapper);

        return new PageResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }

}
