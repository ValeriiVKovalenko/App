package com.example.demo.model.dto.order;

import com.example.demo.model.entity.OrderBottle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {
    private List<OrderBottle> bottles;
    private LocalDate creationDateTime;
    private Long storeId;
}
