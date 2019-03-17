package com.example.unitconverter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "conversion_category")
public class ConversionCategory {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "unit_type", nullable = false)
    private String unitType;

    @Column(name = "base_unit", nullable = false)
    private String baseUnit;
}
