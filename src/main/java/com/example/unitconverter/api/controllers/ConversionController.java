package com.example.unitconverter.api.controllers;

import com.example.unitconverter.api.dtos.ConversionCategoryDto;
import com.example.unitconverter.api.dtos.ConversionFactorDto;
import com.example.unitconverter.service.ConversionCategoryService;
import com.example.unitconverter.service.ConversionFactorService;
import com.example.unitconverter.service.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convert")
public class ConversionController {

    @Autowired
    private ConversionCategoryService conversionCategoryService;

    @Autowired
    private ConversionFactorService conversionFactorService;

    @Autowired
    private UnitConversionService unitConversionService;

    @PostMapping
    public ResponseEntity postCategory(
            @RequestBody final ConversionCategoryDto conversionCategoryDto
    ) {
        conversionCategoryService.createConversionCategory(conversionCategoryDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConversionCategoryDto>> getCategories() {
        return new ResponseEntity<>(conversionCategoryService.getConversionCategories(), HttpStatus.OK);
    }

    @PostMapping("/{unitType}")
    public ResponseEntity postFactor(
            @RequestBody final ConversionFactorDto conversionFactorDto,
            @PathVariable final String unitType
    ) {
        conversionFactorService.createConversionFactor(conversionFactorDto, unitType);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{unitType}")
    public ResponseEntity getFactors(
            @PathVariable final String unitType
    ) {
        conversionFactorService.getConversionFactors(unitType);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
