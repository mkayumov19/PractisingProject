package com.practice.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanWithParam {

    @BeforeAll
    public static void setUpClass(){
        RestAssured.baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void pathParamTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParams("id", 6)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Tedmund"));

    }

    @Test
    public void negativePathParamTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 999)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 404);
        assertEquals(response.contentType(), "application/json");
        response.prettyPrint();
        assertTrue(response.body().asString().contains("Not Found"));

    }

}













