package com.practice.tests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JsonPathSpartan {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 6)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        int id = response.path("id");
        System.out.println("Id = " + id);

        JsonPath jsonData = response.jsonPath();
        int id1 = jsonData.getInt("id");
        String name1 = jsonData.getString("name");
        String gender1 = jsonData.getString("gender");
        long phone1 = jsonData.getLong("phone");

        System.out.println(id1);
        System.out.println(name1);
        System.out.println(gender1);
        System.out.println(phone1);

        assertEquals(id1, 6);
        assertEquals(name1, "Tedmund");
        assertEquals(gender1, "Male");
        assertEquals(phone1, 2227140732l);

    }
}
