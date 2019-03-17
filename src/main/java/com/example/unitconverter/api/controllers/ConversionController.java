package com.example.unitconverter.api.controllers;

import com.example.unitconverter.api.dtos.ConversionCategoryDto;
import com.example.unitconverter.api.dtos.ConversionResponseDto;
import com.example.unitconverter.service.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/convert")
public class ConversionController {

    @Autowired
    private UnitConversionService unitConversionService;

    @PostMapping
    public ResponseEntity postCategory(
            @RequestBody final ConversionCategoryDto conversionCategoryDto
    ) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/weight")
    public ResponseEntity<ConversionResponseDto> getWeightConversions(
            @RequestParam(required = true) final String from,
            @RequestParam(required = true) final Double value
    ) {
        if ("grams".equalsIgnoreCase(from)) {
            final ConversionResponseDto conversion = unitConversionService.getConversionFor(from, value);
            return new ResponseEntity<>(conversion, HttpStatus.OK);
        } else {
            // TODO: implement :)
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
