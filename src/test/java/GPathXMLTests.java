import config.EndPoint;
import config.TestConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class GPathXMLTests extends TestConfig {

    @Test
    public void getFirstGameInList() {

        Response response = get(EndPoint.VIDEOGAMES);
        String name = response.path("videoGames.videoGame.name[0]");
        System.out.println(name);
    }

    /*<videoGames>
         <videoGame category="Shooter" rating="Universal">
             <id>1</id>
             <name> Resident Evil 4</name>
             <releaseDate>2005-10-01T00:00:00+03:00</releaseDate>
             <reviewScore>85</reviewScore>
         </videoGame>*/
    @Test
    public void getAttributeName() {

        Response response = get(EndPoint.VIDEOGAMES);
        String category = response.path("videoGames.videoGame[0].@category");
        System.out.println(category);
    }

    @Test
    public void getListOfXmlNodes() {

        String responseAsString = get(EndPoint.VIDEOGAMES).asString();
        List<Node> allResults = XmlPath.from(responseAsString).get(
                "videoGames.videoGame.findAll { element -> return element }");
        System.out.println(allResults.get(2).get("name").toString());
    }

    @Test
    public void getListOfXmlNodesByFindAllOnAttribute() { //try to find all games with 'Driving' category

        String responseAsString = get(EndPoint.VIDEOGAMES).asString();
        List<Node> allDrivingGames = XmlPath.from(responseAsString).get(
                "videoGames.videoGame.findAll { videoGame -> def category = videoGame.@category; category == 'Driving' }");
        System.out.println(allDrivingGames.get(0).get("name").toString());
    }

    @Test
    public void getSingleElementDepthFirst() { //read about Groovy Depth First Search

        String responseAsString = get(EndPoint.VIDEOGAMES).asString();

        int reviewScore = XmlPath.from(responseAsString).getInt(
                "**.find { it.name == 'Gran Turismo 3'}.reviewScore");
        System.out.println(reviewScore);
    }

    @Test
    public void getAllNodesBasedOnACondition() {

        String responseAsString = get(EndPoint.VIDEOGAMES).asString();
        int reviewScore = 90;
        List<Node> allVideoGamesOverCertainScore = XmlPath.from(responseAsString).get(
                "videoGames.videoGame.findAll { it.reviewScore.toFloat() >= " + reviewScore + "}");
        System.out.println(allVideoGamesOverCertainScore);
    }
}
