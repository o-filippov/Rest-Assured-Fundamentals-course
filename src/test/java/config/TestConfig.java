package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestConfig {

    public static RequestSpecification videoGame_requestSpec;
    public static RequestSpecification football_requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setup() {
        RestAssured.proxy("localhost", 8888); //Uncomment if you use proxy

        videoGame_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(8080).
                setBasePath("/app/").
                addHeader("Content-Type", "application/xml").
                addHeader("Accept", "application/xml").
                build();

        RestAssured.requestSpecification = videoGame_requestSpec; //delete this line if not in use

        football_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.footballl.data.org").
                setBasePath("/v1/").
                addHeader("X-Auth-Token", "fae8b099875d41f395c58dbb7f35556b").
                addHeader("X-Response-Control", "minified").
                build();



        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                build();

        RestAssured.responseSpecification = responseSpec; //delete the line if you don't use it

    }
}
