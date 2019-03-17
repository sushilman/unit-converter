package com.example.unitconverter.repository;

import com.example.unitconverter.entities.ConversionCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversionCategoryRepository extends CrudRepository<ConversionCategory, String> {
    ConversionCategory findByUnitType(String unitType);

    List<ConversionCategory> findAll();
}
