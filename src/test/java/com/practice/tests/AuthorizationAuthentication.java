package com.practice.tests;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class AuthorizationAuthentication {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://52.207.61.129:8000";
    }




}
