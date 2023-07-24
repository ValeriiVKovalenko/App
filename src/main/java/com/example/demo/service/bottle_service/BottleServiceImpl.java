package com.example.demo.service.bottle_service;

import com.example.demo.exception.DatabaseOperationException;
import com.example.demo.model.dto.BottleReqDto;
import com.example.demo.model.dto.BottleResDto;
import com.example.demo.model.dto.BottleSaveDto;
import com.example.demo.repository.BottleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BottleServiceImpl implements BottleService {
    private final BottleRepository bottleRepository;

    @Override
    public BottleSaveDto createBottle(BottleReqDto bottleReqDto) {
        return bottleRepository.createBottle(bottleReqDto);
    }

    @Override
    public String findById(Long bottleId) {
       return bottleRepository.findById(bottleId)
                .map(BottleResDto::getName)
                .orElseThrow(() -> new DatabaseOperationException("Bottle is not exist by id " + bottleId));
    }

    @Override
    public String findByCode(String bottleCode) {
        return bottleRepository.findByCode(bottleCode)
                .map(BottleResDto::getCode)
                .orElseThrow(() -> new DatabaseOperationException("Bottle is not exist by code " + bottleCode));
    }

    @Override
    public BottleResDto updateBottleById(Long id, BottleReqDto bottleReqDto) {
        Optional<BottleResDto> bottleResDto = bottleRepository.updateBottleById(id, bottleReqDto);
        if (bottleResDto.isEmpty()) {
            throw new IllegalStateException("Bottle is not updated.");
        }
        return bottleResDto.get();
    }

    @Override
    public BottleResDto deleteBottleById(Long id) {
        Optional<BottleResDto> bottleResDto = bottleRepository.deleteBottleById(id);
        if (bottleResDto.isEmpty()) {
            throw new IllegalStateException("Bottle is not updated.");
        }
        return bottleResDto.get();
    }
}