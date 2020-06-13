package com.automation.tests.day3;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class ORDSTestsDAy3 {
    @BeforeAll
    public static void setup (){
        baseURI = "http://54.160.71.179:1000/ords/hr";
    }
    /**
     * given parameter is "/regions/{id}
     * when user makes get request
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is europe
     */
    @Test
    @DisplayName("Get region name is europe")
    public void verifyFirstRegion(){
                  given().
                pathParam("id", 1).
                when().get("/regions/{id}").prettyPeek().
                          then().assertThat().
                          statusCode(200).
                  body("region_name", is("Europe")).
                  body("region_id", is(1)).time(lessThan(5L), TimeUnit.SECONDS);
                  //verify that time is less than 5 seconds



    }
    @Test
    public void verifyEmployee(){
        Response response = given().
                                accept(ContentType.JSON).
                          when().
                               get("/employees").prettyPeek();
        JsonPath jsonPath = response.jsonPath();
        String nameOfFirstEmployee = jsonPath.getString("items[0].first_name");
        String nameOfLastEmployee = jsonPath.getString("items[-1].first_name");

        System.out.println("First name of first employee: " + nameOfFirstEmployee);
        System.out.println("First name of last employee: " + nameOfLastEmployee);

        Map<String,Object> firstEmployee = jsonPath.get("items[0]");
        System.out.println(firstEmployee);




    }

}
