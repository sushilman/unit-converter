package com.example.unitconverter.service;

import com.example.unitconverter.api.dtos.ConversionCategoryDto;
import com.example.unitconverter.api.dtos.ConversionFactorDto;
import com.example.unitconverter.api.exceptions.NotFoundException;
import com.example.unitconverter.entities.ConversionCategory;
import com.example.unitconverter.repository.ConversionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversionCategoryService {

    @Autowired
    private ConversionCategoryRepository conversionCategoryRepository;

    @Autowired
    private ConversionFactorService conversionFactorService;

    @Transactional
    public void createConversionCategory(final ConversionCategoryDto conversionCategoryDto) throws NotFoundException {
        final ConversionCategory conversionCategory = ConversionCategory.fromDto(conversionCategoryDto);
        conversionCategoryRepository.save(conversionCategory);
        conversionFactorService.createConversionFactor(new ConversionFactorDto(conversionCategoryDto.getBaseUnit(), 1.0D), conversionCategoryDto.getCategory());
    }

    public List<ConversionCategoryDto> getConversionCategories() {
        final List<ConversionCategory> conversionCategories = conversionCategoryRepository.findAll();

        final List<ConversionCategoryDto> conversionCategoryDtos = conversionCategories
                .stream()
                .map(ConversionCategory::toDto)
                .collect(Collectors.toList());

        return conversionCategoryDtos;
    }

    public ConversionCategory getByCategory(final String category) {
        return conversionCategoryRepository.findByCategory(category);
    }
}
