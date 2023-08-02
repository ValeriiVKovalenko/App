package com.example.demo.repository.bottle;

import com.example.demo.model.dto.bottle.BottleReqDto;
import com.example.demo.model.dto.bottle.BottleResDto;

import java.util.Optional;

public interface BottleRepository {

    Optional<BottleResDto> findById(Long id);

    Optional<BottleResDto> findByCode(String code);

    Optional<BottleResDto> findByName(String name);

    BottleResDto save(BottleReqDto bottleReqDto);

    Optional<BottleResDto> updateById(Long id, BottleReqDto bottleReqDto);

    Optional<BottleResDto> deleteById(Long id);
}