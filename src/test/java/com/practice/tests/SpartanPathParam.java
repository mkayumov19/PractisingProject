package com.practice.tests;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class SpartanPathParam {

    @BeforeAll
    public static void setUpClass(){
        baseURI="http://52.207.61.129:8000";

    }

    @Test
    public void queryParam1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "j")
                .when().get("/api/spartans/search");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("j"));
        response.prettyPrint();
    }

    @Test
    public void queryParams2(){
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "J");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/api/spartans/search");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

    }

}
