package com.practice.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeleteRequestSpartan {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void deleteRequest1(){
        given().pathParam("id", 749)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

    }


}
