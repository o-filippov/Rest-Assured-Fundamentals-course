import config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTests extends TestConfig {

    @Test
    public void getAllCompetitionsOneSeason() {
        given().log().all().
                spec(football_requestSpec).
                queryParam("season", 2016).
                when().
                get("competitions/").
                then().log().status();
    }

    @Test
    public void getTeamCountOneCompetition() {
        given().log().all().
                spec(football_requestSpec).
                when().
                get("competitions/426/teams").
                then().log().body().
                body("count", equalTo(20));
    }

    @Test
    public void getFirstTeamName() {
        given().
                spec(football_requestSpec).
                when().
                get("competitions/426/teams").
                then().
                body("teams.name[0]", equalTo("Hull City FC")); // checks that the first team's name in a list is Hull City FC
    }

    @Test
    public void getAllTeamData() {
        String responseBody = given().spec(football_requestSpec).when().get("competitions/426/teams").asString();
        System.out.println("Response Body: \n" + responseBody);
    }

    @Test
    public void getAllTeamData_doCheckFirst() {
        Response response = given().spec(football_requestSpec).when().get("competitions/426/teams").
                then().
                contentType(ContentType.JSON).
                extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void extractHeaders() {

        Response response = given().spec(football_requestSpec).when().get("competitions/426/teams").
                then().
                contentType(ContentType.JSON).
                extract().response();

        Headers headers = response.getHeaders();

        String contentTypeHeader = response.getHeader("Content-Type");
        System.out.println(contentTypeHeader);


    }

    @Test
    public void extractFirstTeamName() {

        String firstTeamName = given().spec(football_requestSpec).when().
                get("competitions/426/teams").jsonPath().getString("teams.name[0]");

        System.out.println(firstTeamName);
    }

    @Test
    public void extractAllTeamNames() {

        Response response = given().spec(football_requestSpec).
                when().get("competitions/426/teams").
                then().contentType(ContentType.JSON).extract().response();

        List<String> teamNames = response.path("teams.name");

        for (String name : teamNames) {
            System.out.println(name);
        }

    }
}

