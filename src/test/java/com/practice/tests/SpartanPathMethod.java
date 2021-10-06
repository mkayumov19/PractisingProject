package com.practice.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class SpartanPathMethod {

    @BeforeAll
    public static void setUpClass(){
        baseURI="http://52.207.61.129:8000";
    }

    @Test
    public void pathMethod1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 6)
                .when().get("/api/spartans/{id}");
        response.body().prettyPrint();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        System.out.println("id" + response.body().path("id").toString());
        System.out.println("name" + response.body().path("name").toString());
        System.out.println("gender" + response.body().path("gender").toString());
        System.out.println("phone" + response.body().path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id, 6);
        assertEquals(name, "Tedmund");
        assertEquals(gender, "Male");
        assertEquals(phone, 2227140732l);

    }

    @Test
    public void pathMethod2(){
        Response response = get("/api/spartans");
        int firstId = response.path("id[0]");
        String firstName = response.path("name[0]");
        String firstGender = response.path("gender[0]");
        long firstPhone = response.path("phone[0]");
        System.out.println("firstId = " + firstId);
        System.out.println("firstName = " + firstName);
        System.out.println("firstGender = " + firstGender);
        System.out.println("firstPhone = " + firstPhone);

        String last1stName = response.path("name[-1]");
        System.out.println("Last firstName = " + last1stName);

        List<String> names = response.path("name");
        System.out.println(names);
        System.out.println("names count = " + names.size());

        List<Object> phoneNumbers = response.path("phone");
        for (Object phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }

    }

}














