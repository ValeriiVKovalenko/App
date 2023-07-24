package com.example.demo.service.bottle_service;

import com.example.demo.model.dto.BottleReqDto;
import com.example.demo.model.dto.BottleResDto;
import com.example.demo.model.dto.BottleSaveDto;

public interface BottleService {
    BottleSaveDto create(BottleReqDto bottleReqDto);

    String findById(Long bottleId);

    String findByCode(String bottleCode);

    BottleResDto updateById(Long id, BottleReqDto bottleReqDto);

    BottleResDto deleteById(Long id);
}