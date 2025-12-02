package io.github.bonigarcia.webdriver.jupiter.ch10.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

class RestJupiterTest {

    @Test
    void testRest() {
        HttpBinGet get = RestAssured.get("https://httpbin.org/get").then()
                .assertThat().statusCode(200).extract().as(HttpBinGet.class);

        assertThat(get.getHeaders()).containsKey("Accept-Encoding");
        assertThat(get.getOrigin()).isNotBlank();
    }

}
