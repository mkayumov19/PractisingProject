package com.practice.step_defs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpartanWithParam {

    @BeforeClass
    public static void setUpClass(){
        RestAssured.baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void pathTest1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParams("id", 6)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Tedmund"));

    }

}
