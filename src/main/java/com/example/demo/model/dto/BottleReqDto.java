package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BottleReqDto {
    private Long id;
    private String name;
    private Double volume;
    private String packingType;
    private BigDecimal price;
    private String producerCountry;
    private String code;
    private Boolean isDeleted;
}
