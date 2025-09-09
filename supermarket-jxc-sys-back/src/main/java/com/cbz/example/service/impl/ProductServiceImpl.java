package com.cbz.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbz.example.entity.Product;
import com.cbz.example.mapper.ProductMapper;
import com.cbz.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:41:09
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
