import com.example.Tweet;
import com.example.User;
import com.sun.org.apache.regexp.internal.RE;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.asserts.SoftAssert;

import javax.xml.ws.Endpoint;

public class SampleGetTest {

    String Tweetid,id1;

  /*  @Test
    public void GetWeatherDetails()
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();


        // Set HTTP Headers
       *//* httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwiZXhwIjoxNTI5NDIwMjQzfQ.qepmpAizfH9QHNygKM-7fhhkpvJSYMUOQOTKemLeYCcy2E1yrFNxA61YCqio7rWGUmSz3KE2osqlo-7LhWzRTQ");
*//*

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, "/Hyderabad");

        // Now let us print the body of the message to see what response
        // we have recieved from the server
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

    }*/
    @Test
    public void getAuthToken()
    {
        String conusumerKey="3nKecnYWvZJfPajb784QdEu3U";
        String consumerSecretKey="D8A9p0jgienbNVrM12aXrDG5jPFgem2RaoZpNpzE5skrtM0UOB";
        String accessToken="1231887881045544960-uIW5l90qnMAOQt2ood1FcxYcuZSgoC";
                String accessTokenSecret="eQ2tCVldm653PSvKzyH4as4dq1WIj92xKyyykCX6r0R4X";



        RestAssured.baseURI = "http://api.twitter.com/oauth/request_token";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        Response response = RestAssured.given().auth().oauth(conusumerKey,consumerSecretKey,accessToken,accessTokenSecret).
                                            when().post().then().assertThat().statusCode(200).log().all().extract().response();



        // we have recieved from the server
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);


    }

    @Test(priority=1,description="Create a Tweet")
    public void CreateTweet()
    {/*
        RequestSpecification requestSpecification=new RestAssuredConfiguration().getRequestSpecification();
        new RestAssuredConfiguration(requestSpecification,, EndPoint.GOOGLE_API,)*/


        String conusumerKey="3nKecnYWvZJfPajb784QdEu3U";
        String consumerSecretKey="D8A9p0jgienbNVrM12aXrDG5jPFgem2RaoZpNpzE5skrtM0UOB";
        String accessToken="1231887881045544960-uIW5l90qnMAOQt2ood1FcxYcuZSgoC";
        String accessTokenSecret="eQ2tCVldm653PSvKzyH4as4dq1WIj92xKyyykCX6r0R4X";

        RestAssured.baseURI="http://api.twitter.com/1.1/statuses";

        Response Resp=RestAssured.given().
                auth().oauth(conusumerKey, consumerSecretKey, accessToken, accessTokenSecret).
                queryParam("status", "Twitter ggggg jdff").log().all().
                when().
                post("/update.json").
                then().assertThat().statusCode(200).log().all().
                extract().response();

        Tweet tweet= Resp.as(Tweet.class, ObjectMapperType.GSON);
        SoftAssert sa=new SoftAssert();
        sa.assertEquals(new User().getName(),"Veena");
        sa.assertAll();

        String CreateTwe=Resp.asString();
        JsonPath js=new JsonPath(CreateTwe);

        System.out.println("id is"+js.get("id"));

        String Tweetid=(js.get("id")).toString();

        System.out.println("Id of newly Created Tweet is \t"+Tweetid);
    }

    @Test(priority=2,description="Delete a Tweet")
    public void DeleteTweet()
    {
        RestAssured.baseURI="http://api.twitter.com/1.1/statuses";


        String conusumerKey="3nKecnYWvZJfPajb784QdEu3U";
        String consumerSecretKey="D8A9p0jgienbNVrM12aXrDG5jPFgem2RaoZpNpzE5skrtM0UOB";
        String accessToken="1231887881045544960-uIW5l90qnMAOQt2ood1FcxYcuZSgoC";
        String accessTokenSecret="eQ2tCVldm653PSvKzyH4as4dq1WIj92xKyyykCX6r0R4X";

        Response Resp=	RestAssured.given().
                auth().oauth(conusumerKey, consumerSecretKey, accessToken, accessTokenSecret).
                when().
                post("/destroy/"+Tweetid+".json").
                then().assertThat().statusCode(200).log().all().
                extract().response();

        String DelTwe=Resp.asString();
        JsonPath js1=new JsonPath(DelTwe);

      //  logger.info("Delted Tweet id is"+js1.get("id"));

        String id1=(js1.get("id")).toString();

        System.out.println("Tweet has been Delted Sucesfully\t"+id1);

    }

}