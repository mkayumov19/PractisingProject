package com.practice.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class PutRequestSpartan {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Map<String, Object> putMap = new HashMap<>();
        putMap.put("gender", "Male");
        putMap.put("name", "Alexjon");
        putMap.put("phone", 1234567890L);
        given().contentType(ContentType.JSON)
                .and().pathParam("id", 749)
                .and().body(putMap)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

    }

    @Test
    public void putRequest(){
        Map<String, Object> putMap = new HashMap<>();
        putMap.put("gender", "Male");
        putMap.put("name", "Alexjon");
        putMap.put("phone", 1234567890L);
        given().contentType(ContentType.JSON)
                .and().pathParam("id", 749)
                .and().body(putMap)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

    }

    @Test
    public void patchRequest(){
        Map<String, Object> patchMap = new HashMap<>();
        patchMap.put("name", "AlexXon");
        given().contentType(ContentType.JSON)
                .and().pathParam("id", 749)
                .and().body(patchMap)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

    }

}
