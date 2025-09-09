package com.cbz.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbz.example.entity.ProductReservation;
import com.cbz.example.mapper.ProductReservationMapper;
import com.cbz.example.service.ProductReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenbozhi
* @date 2024-5-13 3:41:12
*/
@Service
public class ProductReservationServiceImpl extends ServiceImpl<ProductReservationMapper, ProductReservation> implements ProductReservationService {

}
