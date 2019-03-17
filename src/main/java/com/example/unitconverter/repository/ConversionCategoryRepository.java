package com.example.unitconverter.repository;

import com.example.unitconverter.entities.ConversionCategory;
import org.springframework.data.repository.CrudRepository;

public interface ConversionCategoryRepository extends CrudRepository<ConversionCategory, String> {
    ConversionCategory findByUnitType(String unitType);
}
