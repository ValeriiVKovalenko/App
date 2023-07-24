package com.example.demo.controller;

import com.example.demo.exception.DatabaseOperationException;
import com.example.demo.model.dto.BottleReqDto;
import com.example.demo.model.dto.BottleResDto;
import com.example.demo.model.dto.BottleSaveDto;
import com.example.demo.repository.BottleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bottle")
public class BottleController {

    private final BottleRepo bottleRepo;

    @PostMapping
    public BottleSaveDto createBottle(@RequestBody BottleReqDto bottleReqDto) {
        return bottleRepo.createBottle(bottleReqDto);
    }

    @GetMapping(value = "/{id}")
    public String getBottleById(@PathVariable(name = "id") Long bottleId) {
        return bottleRepo.findById(bottleId)
                .map(BottleResDto::getName)
                .orElseThrow(() -> new DatabaseOperationException("Bottle is not exist by id " + bottleId));
    }

    @GetMapping(value = "/code/{code}")
    public String getBottleByCode(@PathVariable(name = "code") String bottleCode) {
        return bottleRepo.findByCode(bottleCode)
                .map(BottleResDto::getCode)
                .orElseThrow(() -> new DatabaseOperationException("Not found."));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BottleResDto> update(@PathVariable(name = "id") Long id,
                                               @RequestBody BottleReqDto bottleReqDto) {
        bottleReqDto.setId(id);
        return ResponseEntity.ok(bottleRepo.updateBottleById(id, bottleReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BottleResDto> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(bottleRepo.deleteBottleById(id));
    }
}
