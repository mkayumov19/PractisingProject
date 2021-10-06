package com.practice.tests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HamcrestMatcherTests {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://52.207.61.129:8000";
    }

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                    .pathParam("id", 6)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).and()
                .assertThat().contentType("application/json");
    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .pathParam("id", 6)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(6), "name",equalTo("Tedmund"),
                        "gender", equalTo("Male"), "phone", equalTo(2227140732l));

    }

}
