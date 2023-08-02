package com.example.demo.service.order;

import com.example.demo.model.dto.order.OrderInfoDto;
import com.example.demo.model.dto.order.OrderReqDto;

public interface OrderService {
    Long create(OrderReqDto orderReqDto);

    OrderInfoDto getById(Long id);
}
