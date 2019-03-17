package com.example.unitconverter.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionCategoryDto {
    private String unitType;
    private String baseUnit;
}
