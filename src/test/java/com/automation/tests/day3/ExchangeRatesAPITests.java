package com.automation.tests.day3;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ExchangeRatesAPITests {


    @BeforeAll
    public static void setup (){
        baseURI = "http://api.openrates.io";

    }
    @Test
    @DisplayName("Get all latest currency rates")
public void getLatestRates(){
        // after the website link there is q = (key-value )
        // q - query parameter
        // zip - another query parameter
        //with rest assured , we provide query parameters into given () part
        Response response = given().
                            queryParam("base", "USD").
                when().
                get("/latest").prettyPeek();

        // verify that get req to the endpoint was successful
        Headers headers = response.getHeaders();

        String contentType = headers.getValue("Content-Type");
        System.out.println(contentType);
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("base", is("USD"));


        // let's verify that response contains today's date
        // this line on below return's today's date in the required format : yyyy-MM-dd
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().assertThat().body("date", containsString(date));
        // is - same as equals
}
@Test
    public void getHistoryOfRates(){
        Response response = given().
                           queryParam("base", "USD").
                            when().
                            get("/2008-01-02").prettyPeek();
        Headers headers = response.getHeaders();
        response.then().assertThat().
                statusCode(200).
                and().
                body("date",is("2008-01-02") ).
                and().
                body("rates.USD",equalTo(1.0f) );
        // and doesn't have functional role its just a syntax sugar
    Float parameter = response.jsonPath().get("rates.EUR");
    System.out.println(parameter);
}
}
