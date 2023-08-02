package com.example.demo.model.dto.bottle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BottleReqDto {
    private String name;
    private Double volume;
    private String productType;
    private BigDecimal price;
    private String producerCountry;
    private String code;
    private Boolean isDeleted;
}
