import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
/**
 * Created by gveenam on 2/17/2021.
 */
public class GetBDD {

    @Test
    public void test_NoOfCircuits(){


        //To get the No of circuits
        given().
                when().get("http://ergast.com/api/f1/2017/circuit.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId",hasSize(20)).
                and().statusCode(200).
                and().header("Content-Lengthh",equalTo("3334"));

    }

    @Test
    public void testGet(){
        RestAssured.baseURI="http://regres.in/api/users";
        given().
                queryParam("Page","2")
                .body("")
                .when().
                get()
                .then().assertThat().statusCode(200)
                .body("page",equalTo(2));

    }

    @Test
    public void testPost(){
        RestAssured.baseURI="http://regres.in/api/users";

        String userData="Payload the JSON";
        given().body(userData)
                .when().post()
                .then().assertThat().statusCode(201)
                .and().body("updatedAt",is(notNullValue()));
    }


}



