package com.example.unitconverter.entities;

import com.example.unitconverter.api.dtos.ConversionFactorDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class ConversionFactor {
    @Id
    @SequenceGenerator(
            name = "conversion_factor_id_seq",
            sequenceName = "conversion_factor_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conversion_factor_id_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conversion_category_id", referencedColumnName = "id")
    private ConversionCategory conversionCategory;

    @Column(name = "target_unit", nullable = false)
    private String targetUnit;

    @Column(name = "factor", nullable = false)
    private Double factor;

    public static ConversionFactor fromDto(ConversionFactorDto conversionFactorDto, ConversionCategory conversionCategory) {
        final ConversionFactor conversionFactor = new ConversionFactor();
        conversionFactor.setFactor(conversionFactorDto.getFactor());
        conversionFactor.setTargetUnit(conversionFactorDto.getTargetUnit());
        conversionFactor.setConversionCategory(conversionCategory);

        return conversionFactor;
    }

    public static ConversionFactorDto toDto(ConversionFactor conversionFactor) {
        final ConversionFactorDto conversionFactorDto = new ConversionFactorDto();
        conversionFactorDto.setFactor(conversionFactor.getFactor());
        conversionFactorDto.setTargetUnit(conversionFactor.getTargetUnit());

        return conversionFactorDto;
    }
}
