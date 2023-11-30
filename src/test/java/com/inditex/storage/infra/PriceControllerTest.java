package com.inditex.storage.infra;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PriceControllerTest {

    private static final String API_PATH = "api/v1/prices";
    private static final String URL = "http://localhost:8080/";

    @BeforeAll
    public static void setup() {
        baseURI = "http://localhost:8080/";
    }

    @Test
    @DisplayName("Test 1: Request at 10:00 on the 14th of the product 35455 for brand 1 (ZARA)")
    @Order(1)
    void testPricesDay14at10() throws JSONException {
        final JSONObject request = new JSONObject();
        request.put("dateOfApplication", "2020-06-14 10:00:00");
        request.put("productId", "35455");
        request.put("brandId", "1");

        final Response empResponse = given().contentType(ContentType.JSON)
                .body(request.toString())
                .log().all()
                .when()
                .post(URL + API_PATH)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .response();

        final JsonPath jsonResponse = empResponse.jsonPath();
        assertThat(jsonResponse.getString("price")).isEqualTo("35.500");
    }

    @Test
    @DisplayName("Test 2: Request at 16:00 on the 14th of the product 35455 for brand 1 (ZARA)")
    @Order(2)
    void testPricesDay14At16() throws JSONException {
        final JSONObject request = new JSONObject();
        request.put("dateOfApplication", "2020-06-14 16:00:00");
        request.put("productId", "35455");
        request.put("brandId", "1");

        final Response empResponse = given().contentType(ContentType.JSON)
                .body(request.toString())
                .log().all()
                .when()
                .post(URL + API_PATH)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .response();

        final JsonPath jsonResponse = empResponse.jsonPath();
        assertThat(jsonResponse.getString("price")).isEqualTo("25.450");
    }

    @Test
    @DisplayName("Test 3: Request at 21:00 on the 14th of the product 35455 for brand 1 (ZARA)")
    @Order(3)
    void testPricesDay14At21() throws JSONException {
        final JSONObject request = new JSONObject();
        request.put("dateOfApplication", "2020-06-14 21:00:00");
        request.put("productId", "35455");
        request.put("brandId", "1");

        final Response empResponse = given().contentType(ContentType.JSON)
                .body(request.toString())
                .log().all()
                .when()
                .post(URL + API_PATH)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .response();

        final JsonPath jsonResponse = empResponse.jsonPath();
        assertThat(jsonResponse.getString("price")).isEqualTo("35.500");
    }

    @Test
    @DisplayName("Test 4: Request at 10:00 on the 15th of the product 35455 for brand 1 (ZARA)")
    @Order(4)
    void testPricesDay15At10() throws JSONException {
        final JSONObject request = new JSONObject();
        request.put("dateOfApplication", "2020-06-15 10:00:00");
        request.put("productId", "35455");
        request.put("brandId", "1");

        final Response empResponse = given().contentType(ContentType.JSON)
                .body(request.toString())
                .log().all()
                .when()
                .post(URL + API_PATH)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .response();

        final JsonPath jsonResponse = empResponse.jsonPath();
        assertThat(jsonResponse.getString("price")).isEqualTo("30.500");
    }

    @Test
    @DisplayName("Test 5: request at 21:00 on the 16th of the product 35455 for brand 1 (ZARA)")
    @Order(5)
    void testPricesDay16At21() throws JSONException {
        final JSONObject request = new JSONObject();
        request.put("dateOfApplication", "2020-06-16 21:00:00");
        request.put("productId", "35455");
        request.put("brandId", "1");

        final Response empResponse = given().contentType(ContentType.JSON)
                .body(request.toString())
                .log().all()
                .when()
                .post(URL + API_PATH)
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .response();

        final JsonPath jsonResponse = empResponse.jsonPath();
        assertThat(jsonResponse.getString("price")).isEqualTo("38.950");
    }
}
