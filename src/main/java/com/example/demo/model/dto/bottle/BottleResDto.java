package com.example.demo.model.dto.bottle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BottleResDto {
    private Long id;
    private String name;
    private Double volume;
    private String productType;
    private BigDecimal price;
    private String producerCountry;
    private String code;
    private Boolean isDeleted;
}
