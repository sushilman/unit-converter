package com.example.unitconverter.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConvertedUnit {
    private String unit;
    private Double value;
}
