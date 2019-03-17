package com.example.unitconverter.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConversionResponseDto {
    private String from;
    private Double fromValue;
    private List<ConvertedUnit> toUnits;
}
