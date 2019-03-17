package com.example.unitconverter.api.controllers;

import com.example.unitconverter.api.dtos.ConversionResponseDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.fest.assertions.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ConversionControllerIT {

    private static final String BASE_URI = "http://localhost";
    private static final String WEIGHT_CONVERSION_URI = "/convert/weight";

    @LocalServerPort
    protected int port;

    @Test
    public void expectBadRequestForWeightConversionWhenRequiredQueryParamsAreMissing() {
        getConversion(Collections.emptyMap(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getConverterForWeight() {
        final String fromUnit = "grams";
        final Double value  = 100.0;
        final Map<String, String> params = new HashMap<>();
        params.put("from", fromUnit);
        params.put("value",String.valueOf(value));
        final Response rawResponse = getConversion(params, HttpStatus.OK);

        final ConversionResponseDto conversionResponseDto = rawResponse.as(ConversionResponseDto.class);

        assertThat(conversionResponseDto.getFrom()).isEqualTo(fromUnit);
        assertThat(conversionResponseDto.getFromValue()).isEqualTo(value);
    }

    private Response getConversion(final Map<String, String> params, final HttpStatus expectedStatus) {
        return given(getDefaultSpecs())
            .when()
            .queryParams(params)
            .get(WEIGHT_CONVERSION_URI)
            .then()
            .statusCode(expectedStatus.value())
            .extract()
            .response();
    }

    private RequestSpecification getDefaultSpecs() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setBaseUri(BASE_URI);
        builder.setPort(port);
        return builder.build();
    }
}
