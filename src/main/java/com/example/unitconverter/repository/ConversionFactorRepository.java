package com.example.unitconverter.repository;

import com.example.unitconverter.entities.ConversionFactor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversionFactorRepository extends CrudRepository<ConversionFactor, String> {
    List<ConversionFactor> findByConversionCategoryUnitType(String unitType);
}
