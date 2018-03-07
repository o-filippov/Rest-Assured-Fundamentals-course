package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestConfig {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/app/";

        RestAssured.proxy("localhost", 8888);

        RequestSpecification requestSpecification = new RequestSpecBuilder().
                addHeader("Content-Type", "application/json").
                addHeader("Accept", "application/json").
                build();

        RestAssured.requestSpecification = requestSpecification;

        ResponseSpecification responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                build();

        RestAssured.responseSpecification = responseSpecification;
    }
}
