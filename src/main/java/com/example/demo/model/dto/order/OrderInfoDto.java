package com.example.demo.model.dto.order;

import com.example.demo.model.entity.OrderBottle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {
    List<OrderBottle> bottles;
    private LocalDate creationDateTime;
}
