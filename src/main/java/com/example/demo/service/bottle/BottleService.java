package com.example.demo.service.bottle;

import com.example.demo.model.dto.bottle.BottleReqDto;
import com.example.demo.model.dto.bottle.BottleResDto;

public interface BottleService {
    BottleResDto create(BottleReqDto bottleReqDto);

    String findById(Long bottleId);

    String findByCode(String bottleCode);

    BottleResDto updateById(Long id, BottleReqDto bottleReqDto);

    BottleResDto deleteById(Long id);
}