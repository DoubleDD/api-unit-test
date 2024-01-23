package com.example.springboot.http.甘肃.h3c;


import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class RestTemplateTest {

    private static RestTemplate restTemplate = null;


    @BeforeEach
    void setup() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", 8080, PlainSocketFactory.getSocketFactory()));
        ClientConnectionManager ccm    = new ThreadSafeClientConnManager(registry);
        DefaultHttpClient       client = new DefaultHttpClient(ccm);
        HttpParams              params = client.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, 5000);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        params.setParameter(ClientPNames.DEFAULT_HOST, new HttpHost("60.13.54.71", 8080, "http"));

        client.getCredentialsProvider().setCredentials(
                new AuthScope("60.13.54.71", 30119, "iMC RESTful Web Services"),
                new UsernamePasswordCredentials("admin", "admin@gsslt123"));
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
    }


    @Test
    void testOptions() {
        System.out.println(
                restTemplate.optionsForAllow("http://60.13.54.71:30119/imcrs/fault/alarm"));
    }
//
//    @Test
//    public void testQuery() {
//        ListResult<?> result = restTemplate.getForObject(
//            "http://localhost:8080/imcrs/user", ListResult.class);
//        assertEquals(10, result.getData().size());
//        result = restTemplate.getForObject(
//            "http://localhost:8080/imcrs/user?start={start}&size={size}",
//            ListResult.class, 60, 10);
//        assertEquals(5, result.getData().size());
//    }
}
