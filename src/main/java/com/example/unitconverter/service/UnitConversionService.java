package com.example.unitconverter.service;

import com.example.unitconverter.api.dtos.ConversionDto;
import com.example.unitconverter.api.dtos.ConversionFactorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitConversionService {

    @Autowired
    private ConversionFactorService conversionFactorService;

    public List<ConversionDto> getConversionsFor(final String category, final String fromUnit, final Double value) {
        final List<ConversionFactorDto> conversionFactors = conversionFactorService.getConversionFactors(category);

        final ConversionFactorDto conversionFactorFromBaseUnit = conversionFactorService.getConversionFactorFor(category, fromUnit);
        final Double valueInBaseUnit = value / conversionFactorFromBaseUnit.getFactor();

        final List<ConversionDto> conversions = conversionFactors.stream()
                .map(f ->
                        new ConversionDto(f.getTargetUnit(), f.getFactor() * valueInBaseUnit))
                .collect(Collectors.toList());

        return conversions;
    }
}
