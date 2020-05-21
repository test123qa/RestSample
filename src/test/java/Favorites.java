import com.example.Tweet;
import com.example.User;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by gveenam on 5/20/2020.
 */
public class Favorites {

    @Test(priority=1,description="Get a Favorite")
    public void GetFavorite()
    {/*
        RequestSpecification requestSpecification=new RestAssuredConfiguration().getRequestSpecification();
        new RestAssuredConfiguration(requestSpecification,, EndPoint.GOOGLE_API,)*/


        String conusumerKey="3nKecnYWvZJfPajb784QdEu3U";
        String consumerSecretKey="D8A9p0jgienbNVrM12aXrDG5jPFgem2RaoZpNpzE5skrtM0UOB";
        String accessToken="1231887881045544960-uIW5l90qnMAOQt2ood1FcxYcuZSgoC";
        String accessTokenSecret="eQ2tCVldm653PSvKzyH4as4dq1WIj92xKyyykCX6r0R4X";

        RestAssured.baseURI="http://api.twitter.com/1.1/favorites/list.json";

        Response Resp=RestAssured.given().
                auth().oauth(conusumerKey, consumerSecretKey, accessToken, accessTokenSecret).
                queryParam("count", 200).
                queryParam("screen_name","Veena10918346").log().all().
                when().
                get().
                then().assertThat().statusCode(200).log().all().
                extract().response();

       /* Tweet tweet= Resp.as(Tweet.class, ObjectMapperType.GSON);
        SoftAssert sa=new SoftAssert();
        sa.assertEquals(new User().getName(),"Veena");
        sa.assertAll();
*/
        String CreateTwe=Resp.asString();
        JsonPath js=new JsonPath(CreateTwe);

        System.out.println("id is"+js.get("id"));

        String Tweetid=(js.get("id")).toString();

        System.out.println("Id of newly Created Tweet is \t"+Tweetid);
    }

}
