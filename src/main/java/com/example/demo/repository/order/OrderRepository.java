package com.example.demo.repository.order;

import com.example.demo.model.dto.order.OrderInfoDto;
import com.example.demo.model.dto.order.OrderReqDto;

import java.util.Optional;

public interface OrderRepository {
    Long save(OrderReqDto orderReqDto);

    Optional<OrderInfoDto> findById(Long id);
}
