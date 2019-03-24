package com.example.unitconverter.api.controllers;

import com.example.unitconverter.api.dtos.ConversionDto;
import com.example.unitconverter.api.dtos.ConversionsDto;
import com.example.unitconverter.service.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversions")
public class ConversionsController {

    @Autowired
    private UnitConversionService unitConversionService;

    @GetMapping("{category}")
    public ResponseEntity conversions(
        @PathVariable(value = "category") final String category,
        @RequestParam(value = "fromUnit") final String fromUnit,
        @RequestParam(value = "value") final Double value
    ) {
        final List<ConversionDto> conversions = unitConversionService.getConversionsFor(category, fromUnit, value);
        return ResponseEntity.ok(new ConversionsDto(conversions));
    }
}
