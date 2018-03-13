package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

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
                addHeader("Content-Type", "application/json").
                addHeader("Accept", "application/json").
                build();

        RestAssured.requestSpecification = videoGame_requestSpec; //delete this line if not in use

        football_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.football-data.org").
                setBasePath("/v1/").
                addHeader("X-Auth-Token", "3e634ca011e74cf2bc9b9bc210e3454d").
                addHeader("X-Response-Control", "minified").
                build();


        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectResponseTime(lessThan(3000L)).
                build();

        RestAssured.responseSpecification = responseSpec; //delete the line if you don't use it

    }
}
