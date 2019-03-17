package com.example.unitconverter.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.unitconverter.api.dtos.ConversionResponseDto;
import com.example.unitconverter.api.dtos.ConvertedUnit;
import com.example.unitconverter.service.UnitConversionService;

@Service
public class WeightUnitConversionService implements UnitConversionService {

	private static final String GRAMS = "grams";
	private static final String KILO_GRAMS = "kilograms";
	private static final String POUNDS = "pounds";

	@Override
	public ConversionResponseDto getConversionFor(final String from, final Double fromValue) {
		ConvertedUnit convertedFirstUnit = null;
		ConvertedUnit convertedSecondUnit = null;

		// TODO: use DB to save these configurations
		// get units from DB
		// get conversion factor from DB
		switch (from) {
		case GRAMS:
			convertedFirstUnit = new ConvertedUnit(KILO_GRAMS, fromValue / 1000.0);
			convertedSecondUnit = new ConvertedUnit(POUNDS, fromValue * 0.002205);
			break;
		case KILO_GRAMS:
			// TODO: implement for pound and grams
			break;
		case POUNDS:
			// TODO: implement for kilograms and grams
			break;
		default:
			return null;
		}

		final ConversionResponseDto conversionResponseDto = new ConversionResponseDto();
		conversionResponseDto.setFrom(from);
		conversionResponseDto.setFromValue(fromValue);
		conversionResponseDto.setToUnits(Arrays.asList(convertedFirstUnit, convertedSecondUnit));
		return conversionResponseDto;
	}
}
