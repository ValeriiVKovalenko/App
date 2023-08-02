package com.example.demo.controller.order;

import com.example.demo.model.dto.order.OrderInfoDto;
import com.example.demo.model.dto.order.OrderReqDto;
import com.example.demo.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderBottleController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody OrderReqDto orderReqDto) {
        return ResponseEntity.ok(orderService.create(orderReqDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderInfoDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }


}
