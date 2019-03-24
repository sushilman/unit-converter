package com.example.unitconverter.repository;

import com.example.unitconverter.api.dtos.ConversionFactorDto;
import com.example.unitconverter.entities.ConversionFactor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversionFactorRepository extends CrudRepository<ConversionFactor, String> {
    List<ConversionFactor> findByConversionCategoryCategory(String category);

    ConversionFactor findByConversionCategoryCategoryAndTargetUnit(String category, String targetUnit);
}
