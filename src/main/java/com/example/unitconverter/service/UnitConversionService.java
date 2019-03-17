package com.example.unitconverter.service;

import com.example.unitconverter.api.dtos.ConversionResponseDto;
import com.example.unitconverter.api.dtos.ConvertedUnit;
import com.example.unitconverter.entities.ConversionCategory;
import com.example.unitconverter.repository.ConversionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UnitConversionService {

    @Autowired
    ConversionCategoryRepository conversionCategoryRepository;



    public ConversionResponseDto getConversionFor(final String from, final Double fromValue) {

        // TODO: use DB to save these configurations
        // get units from DB
        // get conversion factor from DB
        final ConvertedUnit convertedUnitKg = new ConvertedUnit("kilograms", fromValue / 1000.0);
        final ConvertedUnit convertedUnitPounds = new ConvertedUnit("pounds", fromValue * 0.002205);

        final ConversionResponseDto conversionResponseDto = new ConversionResponseDto();
        conversionResponseDto.setFrom(from);
        conversionResponseDto.setFromValue(fromValue);
        conversionResponseDto.setToUnits(Arrays.asList(convertedUnitKg, convertedUnitPounds));
        return conversionResponseDto;
    }
}
