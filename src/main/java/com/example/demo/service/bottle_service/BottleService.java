package com.example.demo.service.bottle_service;

import com.example.demo.model.dto.BottleReqDto;
import com.example.demo.model.dto.BottleResDto;
import com.example.demo.model.dto.BottleSaveDto;

public interface BottleService {
    BottleSaveDto createBottle(BottleReqDto bottleReqDto);

    String findById(Long bottleId);

    String findByCode(String bottleCode);

    BottleResDto updateBottleById(Long id, BottleReqDto bottleReqDto);

    BottleResDto deleteBottleById(Long id);
}