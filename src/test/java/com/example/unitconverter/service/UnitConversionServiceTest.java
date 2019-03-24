package com.example.unitconverter.service;


import com.example.unitconverter.api.dtos.ConversionDto;
import com.example.unitconverter.api.dtos.ConversionFactorDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UnitConversionServiceTest {

    @Mock
    ConversionFactorService conversionFactorService;

    @InjectMocks
    UnitConversionService unitConversionService;

    @Test
    public void testGetConversionsFor() {
        final String category = "myCategory";
        final String fromUnit = "fromUnit";
        final Double value = 100.0D;

        final ConversionFactorDto conversionFactorDto1 = new ConversionFactorDto("t1", 1.0);
        final ConversionFactorDto conversionFactorDto2 = new ConversionFactorDto("t2", 2.0);
        final List<ConversionFactorDto> conversionFactors = Arrays.asList(conversionFactorDto1, conversionFactorDto2);

        when(conversionFactorService.getConversionFactors(category)).thenReturn(conversionFactors);
        when(conversionFactorService.getConversionFactorFor(category, fromUnit)).thenReturn(conversionFactorDto1);

        final ConversionDto expectedConversionDto1 = new ConversionDto("t1", value * conversionFactorDto1.getFactor());
        final ConversionDto expectedConversionDto2 = new ConversionDto("t1", value * conversionFactorDto1.getFactor());

        final List<ConversionDto> actualConversion = unitConversionService.getConversionsFor(category, fromUnit, value);

        assertThat(actualConversion).contains(expectedConversionDto1, expectedConversionDto2);
    }
}
