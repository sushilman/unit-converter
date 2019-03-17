package com.example.unitconverter.service;

import com.example.unitconverter.api.dtos.ConversionCategoryDto;
import com.example.unitconverter.entities.ConversionCategory;
import com.example.unitconverter.repository.ConversionCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ConversionCategoryServiceTest {

    @Mock
    private ConversionCategoryRepository conversionCategoryRepository;

    @InjectMocks
    private ConversionCategoryService conversionCategoryService;

    @Test
    public void testCreateConversionCategory() {
        ConversionCategoryDto expectedConversionCategory = new ConversionCategoryDto();
        expectedConversionCategory.setCategory("distance");
        expectedConversionCategory.setBaseUnit("meters");

        conversionCategoryService.createConversionCategory(expectedConversionCategory);

        verify(conversionCategoryRepository, times(1)).save(any(ConversionCategory.class));
    }

    @Test
    public void testGetCategories() {

        final ConversionCategory cat1 = new ConversionCategory(1L, "distance", "meters");
        final ConversionCategory cat2 = new ConversionCategory(2L, "weight", "grams");
        final List<ConversionCategory> categories = Arrays.asList(cat1, cat2);

        when(conversionCategoryRepository.findAll()).thenReturn(categories);
        final List<ConversionCategoryDto> actualCategories = conversionCategoryService.getConversionCategories();

        assertThat(actualCategories).extracting("category").containsOnly(cat1.getCategory(), cat2.getCategory());
    }
}
