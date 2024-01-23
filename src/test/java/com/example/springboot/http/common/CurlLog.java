package com.example.springboot.http.common;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class CurlLog {

    private CurlLog() {
        throw new UnsupportedOperationException();
    }

    public static String log(String uuid, Logger logger, String url, HttpMethod httpMethod, HttpHeaders headers, String body) {
        String curl = null;
        switch (httpMethod) {
            case GET:
                curl = getCurl(headers, url);
                logger.info("{} 第三方接口调用 request: {}", uuid, curl);
                break;
            case POST:
            case PUT:
                curl = postCurl(headers, body, url);
                logger.info("{} 第三方接口调用 request: {}", uuid, curl);
                break;
            default:
                break;
        }
        return curl;
    }

    public static <T> void log(String uuid, Logger logger, ResponseEntity<T> response) {
        if (response == null) {
            return;
        }
        logger.info("{} 第三方接口调用 response: {}", uuid, response.getBody());
    }

    public static String curl(String method, String url, Map<String, String> headers, String body) {
        if (StringUtils.isBlank(method)) method = "get";
        method = method.toLowerCase(Locale.ROOT);
        switch (method) {
            case "get":
                return String.format("curl '%s' %s", url, curlHeaderStr(headers));
            default:
                return String.format("curl -X %s '%s' %s -d '%s'", method.toUpperCase(), url, curlHeaderStr(headers), body);
        }
    }

    public static String postCurl(HttpHeaders headers, String body, String url) {
        return String.format("curl -X POST '%s' %s -d '%s'", url, curlHeaderStr(headers), body);
    }


    public static String getCurl(HttpHeaders headers, String url) {
        return String.format("curl '%s' %s", url, curlHeaderStr(headers));
    }

    public static String curlHeaderStr(HttpHeaders headers) {
        Set<Map.Entry<String, List<String>>> entries   = headers.entrySet();
        StringBuilder                        headerStr = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : entries) {
            headerStr.append("-H ").append(String.format("'%s: %s' ", entry.getKey(), String.join(",", entry.getValue())));
        }
        return headerStr.toString();
    }

    public static String curlHeaderStr(Map<String, String> headers) {
        if (headers == null) return "";
        Set<Map.Entry<String, String>> entries   = headers.entrySet();
        StringBuilder                  headerStr = new StringBuilder();
        for (Map.Entry<String, String> entry : entries) {
            headerStr.append("-H ").append(String.format("'%s: %s' ", entry.getKey(), entry.getValue()));
        }
        return headerStr.toString();
    }

}
