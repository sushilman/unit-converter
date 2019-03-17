package com.example.unitconverter.entities;

import com.example.unitconverter.api.dtos.ConversionCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversion_category")
public class ConversionCategory {

    @Id
    @SequenceGenerator(
            name = "conversion_category_id_seq",
            sequenceName = "conversion_category_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conversion_category_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "base_unit", nullable = false)
    private String baseUnit;

    public static ConversionCategory fromDto(final ConversionCategoryDto conversionCategoryDto) {
        final ConversionCategory conversionCategory = new ConversionCategory();
        conversionCategory.setBaseUnit(conversionCategoryDto.getBaseUnit());
        conversionCategory.setCategory(conversionCategoryDto.getCategory());

        return conversionCategory;
    }

    public static ConversionCategoryDto toDto(final ConversionCategory conversionCategory) {
        final ConversionCategoryDto conversionCategoryDto = new ConversionCategoryDto();
        conversionCategoryDto.setBaseUnit(conversionCategory.getBaseUnit());
        conversionCategoryDto.setCategory(conversionCategory.getCategory());

        return conversionCategoryDto;
    }
}
