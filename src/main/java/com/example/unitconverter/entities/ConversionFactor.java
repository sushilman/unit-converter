package com.example.unitconverter.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class ConversionFactor {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conversion_category_id", referencedColumnName = "id")
    private ConversionCategory conversionCategory;

    @Column(name = "target_unit", nullable = false)
    private String targetUnit;

    @Column(name = "base_unit", nullable = false)
    private Double factor;
}
