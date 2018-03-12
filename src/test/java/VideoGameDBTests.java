import config.EndPoint;
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameDBTests extends TestConfig {

    @Test
    public void getAllGames() {

        given().
                when().get(EndPoint.VIDEOGAMES).
                then();
    }

    @Test
    public void createNewGameByJSON() {

        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"MyNewGame\",\n" +
                "  \"releaseDate\": \"2018-03-10T08:44:57.567Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().log().all().
                body(gameBodyJson).
                when().
                post(EndPoint.VIDEOGAMES).
                then();

    }

    @Test
    public void createNewGameByXML() {

        String gameBodyXml = "<videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>12</id>\n" +
                "    <name>Resident Evil 123123</name>\n" +
                "    <releaseDate>2018-10-01T00:00:00+03:00</releaseDate>\n" +
                "    <reviewScore>123321</reviewScore>\n" +
                "  </videoGame>";

        given().
                body(gameBodyXml).
                when().
                post(EndPoint.VIDEOGAMES).
                then();
    }

    @Test
    public void updateVideoGame() {

        String bodyForUpdateJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"MyUpdatedGame\",\n" +
                "  \"releaseDate\": \"2018-03-10T08:44:57.567Z\",\n" +
                "  \"reviewScore\": 51,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().
                body(bodyForUpdateJson).
                when().
                put("/videogames/11").
                then();
    }

    @Test
    public void deleteVideoGame() {
        given().
                when().
                delete("/videogames/11").
                then();
    }

    @Test
    public void getSingleGame() {
        given().
                pathParam("videoGameId", 5).
                when().
                get(EndPoint.SINGLE_VIDEOGAME);
    }

}
