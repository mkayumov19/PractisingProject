package com.practice.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class SpartanTest1 {

    String spartanBaseUrl = "http://52.207.61.129:8000";

    @Test
    public void getSpartan1(){
        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");
        System.out.println("Response statusCode = " + response.statusCode());
        System.out.println("Response body: asString = " + response.body().asString());
        System.out.println("Response body: prettyPrint = " + response.body().prettyPrint());
    }

    @Test
    public void getSpartan2(){
        Response response = RestAssured.get(spartanBaseUrl+"/api/spartans");
        assertEquals(response.statusCode(), 200);
        assertTrue(response.body().asString().contains("Tedmund"));
//        System.out.println(response.body().prettyPrint());
    }

    @Test
    public void getSpartan3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl+"/api/spartans");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
    }

}
