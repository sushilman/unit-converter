package com.example.unitconverter.service;

import com.example.unitconverter.api.dtos.ConversionCategoryDto;
import com.example.unitconverter.entities.ConversionCategory;
import com.example.unitconverter.repository.ConversionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversionCategoryService {

    @Autowired
    private ConversionCategoryRepository conversionCategoryRepository;

    public void createConversionCategory(final ConversionCategoryDto conversionCategoryDto) {
        final ConversionCategory conversionCategory = ConversionCategory.fromDto(conversionCategoryDto);
        conversionCategoryRepository.save(conversionCategory);
    }

    public List<ConversionCategoryDto> getConversionCategories() {
        final List<ConversionCategory> conversionCategories = conversionCategoryRepository.findAll();

        final List<ConversionCategoryDto> conversionCategoryDtos = conversionCategories
                .stream()
                .map(ConversionCategory::toDto)
                .collect(Collectors.toList());

        return conversionCategoryDtos;
    }

    public ConversionCategory findConversionCategoryByUnitType(final String unitType) {
        return conversionCategoryRepository.findByUnitType(unitType);
    }
}
