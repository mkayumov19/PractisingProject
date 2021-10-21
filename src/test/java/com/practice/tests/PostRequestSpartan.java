package com.practice.tests;

import com.practice.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class PostRequestSpartan {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "        \"name\": \"Maxjon\",\n" +
                        "        \"gender\": \"Male\",\n" +
                        "        \"phone\": 1234567890\n" +
                        "    }")
                .when().post("/api/spartans");

        response.prettyPrint();
        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");
        assertEquals(response.path("success"), "A Spartan is Born!");

        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getString("data.name"), "Maxjon");
        assertEquals(jsonPath.getString("data.gender"), "Male");
        assertEquals(jsonPath.getString("data.phone"), "1234567890");

    }

    @Test
    public void postWithMap(){
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "MaxMap");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 1234567890);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans");
        assertEquals(201, response.statusCode() );
        response.prettyPrint();

    }

    @Test
    public void postWithPojo(){
        Spartan spartan = new Spartan();
        spartan.setName("MaxPojo");
        spartan.setGender("Male");
        spartan.setPhone(1234567890L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .and().when().post("api/spartans");
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(), "application/json");
        response.prettyPrint();

        //===========================GET REQUEST===========================

        Response response1 = given().accept(ContentType.JSON)
                .and().pathParam("id", 748)
                .and().when().get("/api/spartans/{id}");
        Spartan spartan1 = response1.body().as(Spartan.class);
        System.out.println(spartan1.toString());


    }

}
