package Client;

import com.google.gson.JsonObject;


import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gveenam on 2/15/2021.
 */
public class RestClient {

    //GET Method
    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse response=closeableHttpClient.execute(httpGet);

        return response;

    }

    //GET Method using heaser

    public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws IOException {
        CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            httpGet.addHeader(entry.getKey(),entry.getValue());

        }
        CloseableHttpResponse response=closeableHttpClient.execute(httpGet);

        return response;

    }

    //POST Method

    public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws Exception {
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url);
        httpPost.setEntity(new StringEntity(entityString));

        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            httpPost.addHeader(entry.getKey(),entry.getValue());
        }

        CloseableHttpResponse httpResponse=closeableHttpClient.execute(httpPost);
        return  httpResponse;


    }
}
