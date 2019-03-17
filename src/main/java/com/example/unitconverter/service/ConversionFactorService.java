package com.example.unitconverter.service;

import com.example.unitconverter.api.dtos.ConversionFactorDto;
import com.example.unitconverter.api.exceptions.NotFoundException;
import com.example.unitconverter.entities.ConversionCategory;
import com.example.unitconverter.entities.ConversionFactor;
import com.example.unitconverter.repository.ConversionFactorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversionFactorService {

    @Autowired
    private ConversionFactorRepository conversionFactorRepository;

    @Autowired
    private ConversionCategoryService conversionCategoryService;

    public void createConversionFactor(final ConversionFactorDto conversionFactorDto, final String category) throws NotFoundException {
        final ConversionCategory conversionCategory = conversionCategoryService.getByCategory(category);

        if (conversionCategory == null) {
            throw new NotFoundException("Category not found.");
        }

        ConversionFactor conversionFactor = ConversionFactor.fromDto(conversionFactorDto, conversionCategory);
        conversionFactorRepository.save(conversionFactor);
    }

    public List<ConversionFactorDto> getConversionFactors(final String category) {
        final List<ConversionFactor> conversionFactors = conversionFactorRepository.findByConversionCategoryCategory(category);

        final List<ConversionFactorDto> conversionFactorDtos = conversionFactors
                .stream()
                .map(ConversionFactor::toDto)
                .collect(Collectors.toList());

        return conversionFactorDtos;
    }

    public ConversionFactorDto getConversionFactorFor(final String category, final String targetUnit) {
        final ConversionFactorDto conversionFactorDto = conversionFactorRepository.findByConversionCategoryCategoryAndTargetUnit(category, targetUnit);
        return conversionFactorDto;
    }
}
