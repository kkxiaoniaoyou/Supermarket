package com.cbz.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbz.example.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:41:09
*/
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
