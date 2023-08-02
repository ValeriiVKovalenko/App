package com.example.demo.controller.bottle;

import com.example.demo.model.dto.bottle.BottleReqDto;
import com.example.demo.model.dto.bottle.BottleResDto;
import com.example.demo.service.bottle.BottleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bottle")
public class BottleController {

    private final BottleService bottleService;

    @PostMapping
    public BottleResDto create(@RequestBody BottleReqDto bottleReqDto) {
        return bottleService.create(bottleReqDto);
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable(name = "id") Long bottleId) {
        return bottleService.findById(bottleId);
    }

    @GetMapping(value = "/code/{code}")
    public String getByCode(@PathVariable(name = "code") String bottleCode) {
        return bottleService.findByCode(bottleCode);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BottleResDto> updateById(@PathVariable(name = "id") Long id,
                                                   @RequestBody BottleReqDto bottleReqDto) {
        return ResponseEntity.ok(bottleService.updateById(id, bottleReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BottleResDto> deleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(bottleService.deleteById(id));
    }
}
