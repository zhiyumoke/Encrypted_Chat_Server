package jilei.springserverdemo;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Http {
    public static String post(JSONObject json, String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            System.out.println("Http post:" + json.toJSONString());
            StringEntity params = new StringEntity(json.toJSONString(), StandardCharsets.UTF_8);
            request.addHeader("Content-type", "application/json; charset=UTF-8");
            request.setHeader("Accept", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode()==200){
                HttpEntity backEntity=response.getEntity();
                return EntityUtils.toString(backEntity,StandardCharsets.UTF_8);
            }
            return response.toString();
        }
    }
}
