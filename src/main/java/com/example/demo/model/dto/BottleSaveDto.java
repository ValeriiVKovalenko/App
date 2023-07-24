package com.example.demo.model.dto;

import lombok.*;

import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BottleSaveDto {
    private Long id;
    private String name;
    private Double volume;
    private String packingType;
    private BigDecimal price;
    private String producerCountry;
    private String code;
    private Boolean isDeleted;
}
