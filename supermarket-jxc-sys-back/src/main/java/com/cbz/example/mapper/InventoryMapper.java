package com.cbz.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbz.example.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:41:05
*/
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {

}
