package com.example.demo.repository;

import com.example.demo.model.dto.BottleReqDto;
import com.example.demo.model.dto.BottleResDto;
import com.example.demo.model.dto.BottleSaveDto;

import java.util.Optional;

public interface BottleRepo {

    Optional<BottleResDto> findById(Long id);

    Optional<BottleResDto> findByCode(String code);

    Optional<BottleResDto> findByName(String name);

    BottleSaveDto createBottle(BottleReqDto bottleReqDto);

    BottleResDto updateBottleById(Long id, BottleReqDto bottleReqDto);

    BottleResDto deleteBottleById(Long id);
}
