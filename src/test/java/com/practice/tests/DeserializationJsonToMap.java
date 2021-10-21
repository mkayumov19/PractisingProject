package com.practice.tests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class DeserializationJsonToMap {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 6)
                .and().when().get("/api/spartans/{id}");
        Map<String, Object> spartanMap = response.body().as(Map.class);
        System.out.println("Name = " + spartanMap.get("name"));

        assertEquals(spartanMap.get("name"), "Tedmund");
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/");
//        response.prettyPrint();
        List<Map<String, Object>> listOfSpartans = response.body().as(List.class);
        System.out.println(listOfSpartans.get(0));
        Map<String, Object> firstSpartan = listOfSpartans.get(0);
        System.out.println(firstSpartan);
        System.out.println(firstSpartan.get("name"));

        int counter = 1;
        for (Map<String, Object> map1 : listOfSpartans) {
            System.out.println(counter + " spartan: " + map1);
            counter++;
        }
    }

}
