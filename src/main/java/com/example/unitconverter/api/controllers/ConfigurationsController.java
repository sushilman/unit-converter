package com.example.unitconverter.api.controllers;

import com.example.unitconverter.api.dtos.ConversionCategoryDto;
import com.example.unitconverter.api.dtos.ConversionFactorDto;
import com.example.unitconverter.api.exceptions.NotFoundException;
import com.example.unitconverter.service.ConversionCategoryService;
import com.example.unitconverter.service.ConversionFactorService;
import com.example.unitconverter.service.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ConfigurationsController {

    @Autowired
    private ConversionCategoryService conversionCategoryService;

    @Autowired
    private ConversionFactorService conversionFactorService;

    @Autowired
    private UnitConversionService unitConversionService;

    @PostMapping
    public ResponseEntity postCategory(
            @RequestBody final ConversionCategoryDto conversionCategoryDto
    ) throws NotFoundException {
        conversionCategoryService.createConversionCategory(conversionCategoryDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConversionCategoryDto>> getCategories() {
        return new ResponseEntity<>(conversionCategoryService.getConversionCategories(), HttpStatus.OK);
    }

    @PostMapping("/{category}")
    public ResponseEntity postFactor(
            @RequestBody final ConversionFactorDto conversionFactorDto,
            @PathVariable final String category
    ) {
        try {
            conversionFactorService.createConversionFactor(conversionFactorDto, category);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<ConversionFactorDto>> getConversionFactors(
            @PathVariable final String category
    ) {
        return new ResponseEntity<>(conversionFactorService.getConversionFactors(category), HttpStatus.OK);
    }

    @GetMapping("/{category}/{targetUnit}")
    public ResponseEntity<ConversionFactorDto> getConversionFactorByTargetUnit(
            @PathVariable final String category,
            @PathVariable final String targetUnit
    ) {
        return new ResponseEntity<>(conversionFactorService.getConversionFactorFor(category, targetUnit), HttpStatus.OK);
    }
}
