package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
public class Bottle {
    private Long id;
    private String name;
    private double volume;
    private String packingType;
    private BigDecimal price;
    private String producerCountry;
}
