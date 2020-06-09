package com.automation.tests.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanTests {
    String BASE_URL = "http://54.196.47.224:8000/";
     // URI (Uniform Resource Identifier) = URL + URN = http://wwww.google.com/index.html
     // URL (Uniform Resource Locator)    = http://www.google.com
     //URN (Uniform Resource Name )       = /index.html

    @Test
    @DisplayName("Get list of all spartans") // optional
    public void getAllSpartans(){
        //401 unauthorized , since we didnt provide credentials request failed
        //how to provide credentials ??
        // there different types of authentication basic,oath ,oath 2.0 , api key , bearer talking etc.
        //spartan app requires basic authentication : username and password
        given().
                   auth().basic("admin", "admin").
                   baseUri(BASE_URL).
                when().
                     get("/api/spartans").prettyPeek().

                then().statusCode(200);

    }
}
