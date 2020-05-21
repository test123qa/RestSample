import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;



/**
 * Created by gveenam on 5/20/2020.
 */
public class Lists {
    @Test(priority=1,description="Get List")
    public void GetList() throws NoSuchAlgorithmException, KeyManagementException, IOException {/*
        RequestSpecification requestSpecification=new RestAssuredConfiguration().getRequestSpecification();
        new RestAssuredConfiguration(requestSpecification,, EndPoint.GOOGLE_API,)*/


        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
            public void checkClientTrusted(X509Certificate[] certs, String authType) { }
            public void checkServerTrusted(X509Certificate[] certs, String authType) { }

        } };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) { return true; }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        /* End of the fix*/

        URL url = new URL("https://api.twitter.com");
        URLConnection con = url.openConnection();
        Reader reader = new InputStreamReader(con.getInputStream());
        while (true) {
            int ch = reader.read();
            if (ch == -1)
                break;
            System.out.print((char) ch);
        }




        String conusumerKey="3nKecnYWvZJfPajb784QdEu3U";
        String consumerSecretKey="D8A9p0jgienbNVrM12aXrDG5jPFgem2RaoZpNpzE5skrtM0UOB";
        String accessToken="1231887881045544960-uIW5l90qnMAOQt2ood1FcxYcuZSgoC";
        String accessTokenSecret="eQ2tCVldm653PSvKzyH4as4dq1WIj92xKyyykCX6r0R4X";

        RestAssured.baseURI="https://api.twitter.com/1.1/lists/list.json";

        Response Resp=RestAssured.given().
                auth().oauth(conusumerKey, consumerSecretKey, accessToken, accessTokenSecret).log().all().
                queryParam("screen_name","twitterdev").
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

      /*  System.out.println("id is"+js.get("id"));

        String Tweetid=(js.get("id")).toString();
*/
        System.out.println("Id of newly Created Tweet is \t"+js);
    }
}
