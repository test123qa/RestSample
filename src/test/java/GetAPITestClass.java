import Base.TestBase;
import Client.RestClient;
import Data.Users;
import Util.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.sun.deploy.util.SessionState;
import com.sun.org.apache.regexp.internal.RE;

import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Created by gveenam on 2/15/2021.
 */
public class GetAPITestClass extends TestBase {
    TestBase testBase;
    RestClient client;
    String url;
    String serviceURL;
    String uri;

    @BeforeMethod
    public void setUp(){
        testBase=new TestBase();
        url=prop.getProperty("URL");
        serviceURL=prop.getProperty("serviceURL");
        uri=url+serviceURL;


    }


    @Test
    public void getTest() throws IOException {
        client=new RestClient();
       //CloseableHttpResponse response= client.get(uri);

        //adding Headers to the get
        HashMap<String,String> headerMap=new HashMap<>();
        headerMap.put("Content-Type","Application/Json");
        headerMap.put("Auth Token","deiw23uu23");
       CloseableHttpResponse response=client.get(uri,headerMap);


        int statuscode= response.getStatusLine().getStatusCode();
        System.out.println(statuscode);
        Assert.assertEquals(statuscode,200);

        String responseString= EntityUtils.toString(response.getEntity(),"UTF-8");
        JsonObject jsonObject=new JsonObject().getAsJsonObject(responseString);

        //single value assertion
        String s=TestUtil.getValueByJPath(jsonObject,"/per-page");
        Assert.assertEquals(s,"test");

        //Json Array value assertions

        //assume the lastname been as the array value inside data .

        String s1=TestUtil.getValueByJPath(jsonObject,"/data[0]/lastName");





        Header header[] =response.getAllHeaders();

        HashMap<String,String> allheaders=new HashMap<String,String>();

        for(Header header1:header){
            allheaders.put(header1.getName(),header1.getValue());
        }

    }

    @Test
    public void PostTest() throws Exception {
        client=new RestClient();

        //Add Headers
        HashMap<String,String> headerMap=new HashMap<>();
        headerMap.put("Content-Type","Application/Json");
        headerMap.put("Auth Token","deiw23uu23");

        //Generate Json
        //Jackson API required to do Marshelling

        Users users=new Users("test","Employee");

        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();

            // get Oraganisation object as a json string
        //It will create user.json at run time
        Obj.writeValue(new File("/user.json"),users);

        //Object to Json String

        String jsonString= Obj.writeValueAsString(users);
       CloseableHttpResponse httpResponse= client.post(url,jsonString,headerMap);
     int statusCode=  httpResponse.getStatusLine().getStatusCode();

     Assert.assertEquals(statusCode,201);

     //get Json string
        String respString= EntityUtils.toString(httpResponse.getEntity(),"UTF-8");

       // JsonObject jsonObject=new JsonObject(respString);


        //Converting response into the Java object. Unmarshelling
        Users users1=Obj.readValue(respString,Users.class);
        System.out.println(users1);

        Assert.assertEquals(users.getName(),users1.getName());
        Assert.assertEquals(users.getJob(),users1.getJob());

        Assert.assertEquals(users,users1);





    }



}
