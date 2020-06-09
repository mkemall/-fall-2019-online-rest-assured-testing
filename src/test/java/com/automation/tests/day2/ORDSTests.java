package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;

public class ORDSTests {

    String BASE_URL = "http://54.196.47.224:1000/ords/hr";
    @Test
    @DisplayName("Get list of all employees")
    public void getAllEmployees(){
        Response response = given().
                             baseUri(BASE_URL).
                           when().
                          get("/employees").prettyPeek();

    }

    @Test
    @DisplayName("Get employee under specific ID")
    public void getOneEmployee(){
        Response response = given().
                baseUri(BASE_URL).when().get("/employees/{id}",100).prettyPeek();
        response.then().statusCode(200); // to verify that status is 200

        int statusCode = response.statusCode(); // to save status code in variable

        Assertions.assertEquals(200, statusCode);
        /**
         * given base URI = http://54.196.47.224:1000/ords/hr
         * when user sends get request to "/countries"
         * then user verifies that status code is 200
         */


    }
    @Test
    @DisplayName("Get list of all countries")
    public void getAllCountries(){
                given().
                baseUri(BASE_URL).
                when().
                get("/countries").prettyPeek().
                then().
                statusCode(200);
    }
}
