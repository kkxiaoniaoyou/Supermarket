package com.cbz.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbz.example.entity.PurchaseOrder;
import com.cbz.example.mapper.PurchaseOrderMapper;
import com.cbz.example.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:41:18
*/
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements PurchaseOrderService {

}
