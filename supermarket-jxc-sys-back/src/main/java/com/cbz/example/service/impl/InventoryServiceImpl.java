package com.cbz.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbz.example.entity.Inventory;
import com.cbz.example.mapper.InventoryMapper;
import com.cbz.example.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:41:05
*/
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

}
