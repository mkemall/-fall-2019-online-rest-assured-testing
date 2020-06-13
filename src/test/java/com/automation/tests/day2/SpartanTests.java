package com.automation.tests.day2;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SpartanTests {
    String BASE_URL = "http://54.152.21.73:8000";
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
    @Test
    @DisplayName("Add new spartan")
    public void addNewSpartan(){
        //JSON supports different data types : string , integer , boolean
        String body = "{\"gender\": \"Male\", \"name\": \"Random User\", \"phone\": 1234589777}";
        // use file class to read JSON and pass it into body
        //provide path to JSON as a parameter
        File jsonFile = new File(System.getProperty("user.dir") + "/spartan.json");
        given().
                contentType(ContentType.JSON).
                auth().basic("admin", "admin").
                body(body).
                baseUri(BASE_URL).
        when().
              post("/api/spartans").prettyPeek().
        then().statusCode(201);
    }
    @Test
    @DisplayName("Delete some spartan and verify that status code is 204")
    public void deleteSpartanTest(){
        // {id} - path parameter
        given().
                auth().basic("admin", "admin").
                baseUri(BASE_URL).
                when().
                    delete("/api/spartans/{id}",6).prettyPeek().
                then().
                     statusCode(204);
    }
}
