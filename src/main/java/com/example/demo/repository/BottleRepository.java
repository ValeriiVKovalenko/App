package com.example.demo.repository;

import com.example.demo.model.dto.BottleReqDto;
import com.example.demo.model.dto.BottleResDto;
import com.example.demo.model.dto.BottleSaveDto;

import java.util.Optional;

public interface BottleRepository {

    Optional<BottleResDto> findById(Long id);

    Optional<BottleResDto> findByCode(String code);

    Optional<BottleResDto> findByName(String name);

    BottleSaveDto create(BottleReqDto bottleReqDto);

    Optional<BottleResDto> updateById(Long id, BottleReqDto bottleReqDto);

    Optional<BottleResDto> deleteById(Long id);
}
