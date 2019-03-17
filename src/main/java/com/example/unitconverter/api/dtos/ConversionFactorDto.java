package com.example.unitconverter.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionFactorDto {
    private String targetUnit;
    private Double factor;
}
