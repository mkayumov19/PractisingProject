package com.practice.tests;

import com.google.gson.Gson;
import com.practice.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class DeserializationToPOJO {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 6)
                .when().get("/api/spartans/{id}");

        Spartan spartan = response.body().as(Spartan.class);
            System.out.println(spartan.toString());
            System.out.println(spartan.getId());
            System.out.println(spartan.getName());
            System.out.println(spartan.getGender());
            System.out.println(spartan.getPhone());

        assertEquals(spartan.getName(), "Tedmund");
        assertEquals(spartan.getGender(), "Male");
        assertEquals(spartan.getId(), 6);
    }

    @Test
    public void test2(){

    //TODO Deserialization:
        Gson gson = new Gson();
        String myJsonBody = "{\n" +
                "    \"id\": 6,\n" +
                "    \"name\": \"Tedmund\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 2227140732\n" +
                "}";
        Spartan spartanTedmund = gson.fromJson(myJsonBody, Spartan.class);
        System.out.println(spartanTedmund.toString());

    //TODO Serialization:
        Spartan spartan = new Spartan(6, "Tedmund", "Tedmund", 2227140732l);
        String jsonBody = gson.toJson(spartan);
        System.out.println(jsonBody);


    }

}
