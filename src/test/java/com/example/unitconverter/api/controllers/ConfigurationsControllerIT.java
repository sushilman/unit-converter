package com.example.unitconverter.api.controllers;

import com.example.unitconverter.api.dtos.ConversionCategoryDto;
import com.example.unitconverter.api.dtos.ConversionFactorDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:cleandb.sql")
public class ConfigurationsControllerIT {

    private static final String BASE_PATH = "/configurations";
    private static final String CATEGORY_PLACEHOLDER = "category";
    private static final String FACTORS_PATH = BASE_PATH + "/{" + CATEGORY_PLACEHOLDER + "}";

    @LocalServerPort
    private int port;

    @Value("${deploymentTarget}")
    private String deploymentTarget;


    /* Tests for /configurations */
    @Test
    public void testPostConversionCategory() {
        final ConversionCategoryDto conversionCategoryDto = new ConversionCategoryDto("distance", "meters");
        postConversionCategory(conversionCategoryDto, HttpStatus.CREATED);
    }

    @Test
    public void testGetConversionCategories() {
        final String categoryDistance = "distance";
        final String categoryWeight = "weight";

        final ConversionCategoryDto distanceConfig = new ConversionCategoryDto(categoryDistance, "meters");
        postConversionCategory(distanceConfig, HttpStatus.CREATED);

        final ConversionCategoryDto weightConfig = new ConversionCategoryDto(categoryWeight, "grams");
        postConversionCategory(weightConfig, HttpStatus.CREATED);

        final Response rawResponse = getConversionsCategories(HttpStatus.OK);
        final List<ConversionCategoryDto> conversionCategories = rawResponse.as(List.class);

        assertThat(conversionCategories).extracting("category").containsOnly(categoryDistance, categoryWeight);
    }

    private Response getConversionsCategories(final HttpStatus expectedStatus) {
        return given(getDefaultSpecs())
                .when()
                .get(BASE_PATH)
                .then()
                .statusCode(expectedStatus.value())
                .extract()
                .response();
    }

    private Response postConversionCategory(final ConversionCategoryDto conversionCategoryDto, final HttpStatus expectedStatus) {
        return given(getDefaultSpecs())
                .when()
                .body(conversionCategoryDto)
                .post(BASE_PATH)
                .then()
                .statusCode(expectedStatus.value())
                .extract()
                .response();
    }

    /* End of tests for /configurations */



    /* Tests for /configurations/{category} */

    @Test
    public void testPostFactor() {
        final String category = "distance";

        final ConversionCategoryDto conversionCategoryDto = new ConversionCategoryDto(category, "meters");
        postConversionCategory(conversionCategoryDto, HttpStatus.CREATED);

        final ConversionFactorDto conversionFactorDto = new ConversionFactorDto(category, 0.001D);
        postConversionFactor(category, conversionFactorDto, HttpStatus.CREATED);
    }

    @Test
    public void testGetFactors() {
        final String category = "distance";

        final ConversionCategoryDto conversionCategoryDto = new ConversionCategoryDto(category, "meters");
        postConversionCategory(conversionCategoryDto, HttpStatus.CREATED);

        final ConversionFactorDto kilometerFactor = new ConversionFactorDto("kilometers", 0.001D);
        postConversionFactor(category, kilometerFactor, HttpStatus.CREATED);

        final ConversionFactorDto poundFactor = new ConversionFactorDto("miles", 0.0006213712);
        postConversionFactor(category, poundFactor, HttpStatus.CREATED);

        final Response rawResponse = getConversionsFactors(category, HttpStatus.OK);
        final List<ConversionFactorDto> conversionFactors = rawResponse.as(List.class);

        assertThat(conversionFactors).extracting("targetUnit").containsOnly(kilometerFactor.getTargetUnit(), poundFactor.getTargetUnit());
    }

    private Response getConversionsFactors(final String category, final HttpStatus expectedStatus) {
        return given(getDefaultSpecs())
                .when()
                .pathParam(CATEGORY_PLACEHOLDER, category)
                .get(FACTORS_PATH)
                .then()
                .statusCode(expectedStatus.value())
                .extract()
                .response();
    }

    private Response postConversionFactor(final String category, final ConversionFactorDto conversionCategoryDto, final HttpStatus expectedStatus) {
        return given(getDefaultSpecs())
                .when()
                .body(conversionCategoryDto)
                .pathParam(CATEGORY_PLACEHOLDER, category)
                .post(FACTORS_PATH)
                .then()
                .statusCode(expectedStatus.value())
                .extract()
                .response();
    }
    /* End of Tests for /configurations/{category} */

    private RequestSpecification getDefaultSpecs() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setBaseUri(deploymentTarget);
        builder.setPort(port);
        return builder.build();
    }
}
