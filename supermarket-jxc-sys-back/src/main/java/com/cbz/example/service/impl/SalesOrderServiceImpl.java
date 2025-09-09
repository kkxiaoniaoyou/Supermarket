package com.cbz.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbz.example.entity.SalesOrder;
import com.cbz.example.mapper.SalesOrderMapper;
import com.cbz.example.service.SalesOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:41:25
*/
@Service
public class SalesOrderServiceImpl extends ServiceImpl<SalesOrderMapper, SalesOrder> implements SalesOrderService {

}
