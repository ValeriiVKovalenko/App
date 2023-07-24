package com.example.demo.service.bottle_service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.BottleReqDto;
import com.example.demo.model.dto.BottleResDto;
import com.example.demo.model.dto.BottleSaveDto;
import com.example.demo.repository.BottleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BottleServiceImpl implements BottleService {
    private final BottleRepository bottleRepository;

    @Override
    public BottleSaveDto create(BottleReqDto bottleReqDto) {
        return bottleRepository.create(bottleReqDto);
    }

    @Override
    public String findById(Long bottleId) {
       return bottleRepository.findById(bottleId)
                .map(BottleResDto::getName)
                .orElseThrow(() -> new NotFoundException(bottleId));
    }

    @Override
    public String findByCode(String bottleCode) {
        return bottleRepository.findByCode(bottleCode)
                .map(BottleResDto::getCode)
                .orElseThrow(() -> new NotFoundException("Bottle is not exist by code " + bottleCode));
    }

    @Override
    public BottleResDto updateById(Long bottleId, BottleReqDto bottleReqDto) {
        return bottleRepository.updateById(bottleId, bottleReqDto)
                .orElseThrow(() -> new NotFoundException(bottleId));
    }

    @Override
    public BottleResDto deleteById(Long bottleId) {
     return bottleRepository.deleteById(bottleId)
             .orElseThrow(() -> new NotFoundException(bottleId));
    }
}