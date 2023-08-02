package com.example.demo.service.order;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.order.OrderInfoDto;
import com.example.demo.model.dto.order.OrderReqDto;
import com.example.demo.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Long create(OrderReqDto orderReqDto) {
        return orderRepository.save(orderReqDto);
    }

    @Override
    public OrderInfoDto getById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order is not found by id " + orderId));
    }
}