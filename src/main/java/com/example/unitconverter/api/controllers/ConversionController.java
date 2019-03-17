package com.example.unitconverter.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.unitconverter.api.dtos.ConversionResponseDto;
import com.example.unitconverter.service.UnitConversionService;

@RestController
@RequestMapping("/convert")
public class ConversionController {

	@Autowired
    private UnitConversionService unitConversionService;

    @GetMapping("/weight")
    public ResponseEntity<ConversionResponseDto> getWeightConversions(
            @RequestParam(required = true) final String from,
            @RequestParam(required = true) final Double value
    ) {
		final ConversionResponseDto conversion = unitConversionService.getConversionFor(from, value);
		if (conversion != null) {
			return new ResponseEntity<>(conversion, HttpStatus.OK);
		} else {
			// TODO: implement :)
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
    }
}
