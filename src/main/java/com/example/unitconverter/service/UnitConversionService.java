package com.example.unitconverter.service;

import com.example.unitconverter.api.dtos.ConversionResponseDto;

public interface UnitConversionService {
	public ConversionResponseDto getConversionFor(final String from, final Double fromValue);
}
