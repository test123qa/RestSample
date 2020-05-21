import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
/**
 * Created by gveenam on 5/19/2020.
 */
public class RestAssuredConfiguration {

    @BeforeSuite
    public void configure(){
        RestAssured.baseURI="";
        RestAssured.basePath="";
    }

    public RequestSpecification getRequestSpecification(){
        return RestAssured.given().contentType(ContentType.JSON);
    }

    public Response getResponse(RequestSpecification requestSpecification, String endPoint, String status){

        Response ressponse=requestSpecification.get(endPoint);
        Assert.assertEquals(ressponse.getStatusCode(),status);
        ressponse.then().log().all();
        return ressponse;


    }
}
