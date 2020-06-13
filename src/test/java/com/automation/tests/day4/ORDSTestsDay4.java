package com.automation.tests.day4;
import com.automation.utlilities.ConfigurationReader;
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


public class ORDSTestsDay4 {
    @BeforeAll
    public static void setup () {
        baseURI = ConfigurationReader.getProperty("ORDS.URI");
        /**
         * WARM - UP  !
         * Given accept type is JSON
         * WHen users sends a GET request to "/employees"
         * Then status code is 200
         * And content type is application/json
         * And response time is less than 3 seconds
         */
    }

    @Test
        @DisplayName("Verify status code , content type and response time")
        public void employeesTest1(){
        given().
            accept(ContentType.JSON).
         when().
             get("/employees").
         then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                time(lessThan(3L),TimeUnit.SECONDS);

        }


}
