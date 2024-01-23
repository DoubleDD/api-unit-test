package com.example.springboot.http.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import kotlin.Pair;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class BaseTest {
    protected final ThreadLocal<String>  localMsg   = new ThreadLocal<>();
    protected final ThreadLocal<Boolean> formatJson = new ThreadLocal<>();
    protected final String               USER_HOME  = System.getProperty("user.home");

    protected final ThreadLocal<Pair<String, String>> appKeySecretPair = new ThreadLocal<>();
    //    public RestTemplate           restTemplate;
    public          ResponseEntity<String>            response;
    public          ServerApi                         server;

    public static String getSha1(String str) {

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] md  = mdTemp.digest();
            int    j   = md.length;
            char[] buf = new char[j * 2];
            int    k   = 0;
            for (byte byte0 : md) {
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, String> buildParams(String queryStr) {
        Map<String, String> kvMap = new HashMap<>();
        if (StringUtils.isBlank(queryStr)) {
            return kvMap;
        }
        queryStr += "&";
        String[] kvs = queryStr.split("&");
        for (String kv : kvs) {
            int equalIndex = kv.indexOf('=');
            if (equalIndex > 0) {
                String key   = kv.substring(0, equalIndex);
                String value = null;
                try {
                    // 先解码
                    value = URLDecoder.decode(kv.substring(equalIndex + 1), StandardCharsets.UTF_8.name());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                kvMap.put(key, value);
            }
        }
        return kvMap;
    }

    public static String getFormUrlencoded(Map<String, String> params) {
        List<String> argList = new ArrayList<>();
        params.forEach((k, v) -> {
            try {
                argList.add(k + "=" + Optional.ofNullable(URLEncoder.encode(v, "utf-8")).orElse(""));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        return String.join("&", argList);
    }

    @BeforeEach
    public void setUp() {
        server = getServer();
    }

    public void setLocalMsg(String msg) {
        localMsg.set(msg);
    }

    public void clearLocalMsg() {
        localMsg.remove();
    }

    public RestTemplate getRestTemplate(Boolean https) {
        SimpleClientHttpRequestFactory factory = https ? new HttpsClientRequestFactory() : new SimpleClientHttpRequestFactory();
        if (server.isProxy()) {
            factory.setProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1080)));
        }
        factory.setConnectTimeout(10 * 1000);
        // 10分钟超时时间
        factory.setReadTimeout(10 * 60 * 1000);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);

        //对中文格式数据进行处理
//        FormHttpMessageConverter      fc              = new FormHttpMessageConverter();
//        StringHttpMessageConverter    stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
//        List<HttpMessageConverter<?>> partConverters  = new ArrayList<>();
//        partConverters.add(stringConverter);
//        partConverters.add(new ResourceHttpMessageConverter());
//        fc.setPartConverters(partConverters);
//        restTemplate.getMessageConverters().addAll(Arrays.asList(fc,new MappingJackson2HttpMessageConverter()));

        return restTemplate;
    }

    protected abstract ServerApi getServer();

    @AfterEach
    public void after() {
        after("");
    }

    public void after(String uuid) {
        if (response == null) return;
        System.out.println("============================\r\n");
        String       body         = response.getBody();
        boolean      isJson       = false;
        HttpHeaders  headers      = response.getHeaders();
        List<String> contentTypes = headers.get("Content-Type");
        if (contentTypes != null && !contentTypes.isEmpty()) {
            String contentType = contentTypes.get(0);
            if (contentType.contains("application/json")) {
                isJson = true;
            }
        }
        assert body != null;
        if (body.startsWith("{") || body.startsWith("[")) {
            isJson = true;
        }
        if (isJson) {
            Boolean aBoolean = formatJson.get();
            try {
                if (body.startsWith("{")) {
                    JSONObject object = JSONObject.parseObject(body);
                    if (Boolean.TRUE.equals(aBoolean)) {
                        body = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
                    } else {
                        body = JSON.toJSONString(object);
                    }
                } else if (body.startsWith("[")) {
                    JSONArray object = JSONObject.parseArray(body);
                    if (Boolean.TRUE.equals(aBoolean)) {
                        body = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
                    } else {
                        body = JSON.toJSONString(object);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(uuid + "\n" + body);
//        assert response.getStatusCodeValue() == 200;
    }

    protected ResponseEntity<String> get(String path, HttpHeaders headers) {
        return request(path, HttpMethod.GET, new HttpEntity<>(headers));
    }

    protected ResponseEntity<String> get(String path) {
        return request(path, HttpMethod.GET, null);
    }

    protected ResponseEntity<String> get(String path, Map<String, String> header) {
        HttpHeaders headers = new HttpHeaders();
        if (header != null && !header.isEmpty()) {
            header.forEach(headers::add);
        }
        return get(path, headers);
    }

    protected ResponseEntity<String> post(String path, String body, MediaType type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        return request(path, HttpMethod.POST, httpEntity);
    }

    protected ResponseEntity<String> post(String path, String body, HttpHeaders headers) {
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        return request(path, HttpMethod.POST, httpEntity);
    }

    protected ResponseEntity<String> request(String path, HttpMethod httpMethod, HttpEntity<String> httpEntity) {
        return request(path, httpMethod, httpEntity, false);
    }

    protected ResponseEntity<String> request(String path, HttpMethod httpMethod, String body, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        return request(path, httpMethod, httpEntity);
    }

    protected <T> ResponseEntity<String> request(String path, HttpMethod httpMethod, HttpEntity<T> httpEntity, boolean https) {
        String remark = localMsg.get();
        if (StringUtils.isBlank(remark)) {
            remark = "default";
        }
        String uuid = "[" + remark + "]" + UUID.randomUUID();
        try {
            if (server == null) {
                server = getServer();
            }
            String url = ServerApi.build(server, path);
            url = handleUrlParams(url, null);
            UriComponentsBuilder builder          = UriComponentsBuilder.fromHttpUrl(url);
            HttpHeaders          headers          = new HttpHeaders();
            Pair<String, String> appKeySecretPair = this.appKeySecretPair.get();
            String               appKey           = server.getAppKey();
            String               secret           = server.getSecret();
            if (appKeySecretPair != null) {
                if (StringUtils.isNotBlank(appKeySecretPair.getFirst())) {
                    appKey = appKeySecretPair.getFirst();
                    secret = appKeySecretPair.getSecond();
                }
            }

            if (StringUtils.isNotBlank(appKey) && StringUtils.isNotBlank(secret)) {
                String timestamp = System.currentTimeMillis() + "";
                String nonce     = UUID.randomUUID().toString();
//                String nonce = "123123123";
                String sign = getSign(appKey, secret, timestamp, nonce);
                headers.add("appKey", appKey);
                headers.add("timestamp", timestamp);
                headers.add("nonce", nonce);
                headers.add("sign", sign);
            }
            String token = getToken();
            if (StringUtils.isNotBlank(token)) headers.add("token", token);
            T body = null;
            if (httpEntity != null) {
                headers.addAll(httpEntity.getHeaders());
                body = httpEntity.getBody();
            }
            HttpHeaders commonHeaders = commonHeaders();
            if (commonHeaders != null) {
                headers.addAll(commonHeaders);
            }
//            if (!ServerApi.NONE.equals(server)) {
//                headers.add("feign", "2D74F561-0D80-2197-FD26-F0FAC2BFC7CD");
            if (withFeign()) {
                headers.add("feign", "2D74F561-0D80-2197-FD26-F0FAC2BFC7CD");
            }
            HttpEntity<T> entity = new HttpEntity<>(body, headers);
            String        curl   = CurlLog.curl(httpMethod.name(), url, headers.toSingleValueMap(), body == null ? "" : body.toString());
            System.out.println(uuid + "\n" + curl);
            System.out.println("\n============================");
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            ResponseEntity<byte[]> exchange = getRestTemplate(https).exchange(builder.build(true).toUri(), httpMethod, entity, byte[].class);
            stopWatch.stop();
            System.out.println("请求耗时（单位：秒）" + stopWatch.getTotalTimeSeconds());
            HttpHeaders respHeaders = exchange.getHeaders();
            byte[]      bodyBytes   = exchange.getBody();
            assert bodyBytes != null;
            ContentDisposition contentDisposition = respHeaders.getContentDisposition();
            if (contentDisposition.isAttachment()) {
                String filename = contentDisposition.getFilename();
                if (StringUtils.isNotEmpty(filename)) {
                    String userHome = System.getProperty("user.home");
                    String filePath = userHome + "/Downloads/" + URLDecoder.decode(filename);
                    Path   fd       = Paths.get(filePath);
                    if (!Files.exists(fd)) Files.createFile(fd);
                    Files.write(fd, bodyBytes);
                    System.out.println("filePath = " + filePath);
                    return null;
                }
            }
            String responseBody = new String(bodyBytes);
            response = new ResponseEntity<>(responseBody, respHeaders, exchange.getStatusCode());
        } catch (HttpStatusCodeException e) {
            int    statusCode = e.getRawStatusCode();
            String body       = e.getResponseBodyAsString();
            response = new ResponseEntity<>(body, HttpStatus.valueOf(statusCode));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        after(uuid);
        return response;
    }

    protected boolean withFeign() {
        return true;
    }

    protected String getToken() {
        return "";
    }

    protected HttpHeaders commonHeaders() {
        return new HttpHeaders();
    }


    public String handleUrlParams(String url, Map<String, String> params) {
        if (params == null) params = new HashMap<>();
        URI    uri         = URI.create(url);
        String queryString = uri.getRawQuery();
        if (StringUtils.isNotBlank(queryString)) {
            Map<String, String> paramMap = buildParams(queryString);
            params.putAll(paramMap);
        }
        String formUrlencoded = getFormUrlencoded(params);
        if (StringUtils.isNotBlank(formUrlencoded)) {
            if (url.contains("?")) {
                url = url.substring(0, url.indexOf('?')) + "?" + formUrlencoded;
            } else {
                //url后面无参数
                url = (url + "?").replaceAll("\\?+", "\\?") + formUrlencoded;
            }
        }
        return url;
    }

    public String getSign(String appKey, String secret, String timestamp, String nonce) {
        System.out.println("appKey = " + appKey);
        System.out.println("secret = " + secret);
        System.out.println("timestamp = " + timestamp);
        System.out.println("nonce = " + nonce);
        String data = secret + Optional.ofNullable(appKey).orElse("") + Optional.ofNullable(timestamp).orElse("") + Optional.ofNullable(nonce).orElse("") + secret;
        System.out.println("data = " + data);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(data.getBytes(StandardCharsets.UTF_8));
            byte[]        digest = messageDigest.digest();
            StringBuilder result = new StringBuilder();
            System.out.println();
            for (byte b : digest) {
                String str = Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6);
                result.append(str);
            }
            String sign = result.toString();
            System.out.println("sign = " + sign);
            return sign;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void post(@NotNull String path, @NotNull MultiValueMap<String, Object> paramMap, @NotNull MediaType type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
        request(path, HttpMethod.POST, httpEntity, false);
    }

    public void postFile(@NotNull String path, HttpHeaders headers, @NotNull String... fileNames) {
        postFiles(path, "file", headers, null, fileNames);
    }

    public void postFile(@NotNull String path, @NotNull String... fileNames) {
        postFiles(path, "file", null, null, fileNames);
    }

    public void postFiles(@NotNull String path, HttpHeaders headers, @NotNull String... fileNames) {
        postFiles(path, "files", headers, null, fileNames);
    }

    public void postFiles(@NotNull String path, @NotNull String... fileNames) {
        postFiles(path, "files", null, null, fileNames);
    }

    public void postFiles(String path, String paramName, HttpHeaders customHeaders, Map<String, String> body, String[] fileNames) {
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        List<Object>                  list     = new ArrayList<>();
        for (String fileName : fileNames) {
            list.add(new FileSystemResource(new File(fileName.trim())));
        }
        paramMap.put(paramName, list);
        if (body != null) {
            body.forEach((k, v) -> {
                paramMap.put(k, Lists.list(v));
            });
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        if (customHeaders != null) {
            Set<Map.Entry<String, List<String>>> entries = customHeaders.entrySet();
            for (Map.Entry<String, List<String>> entry : entries) {
                headers.put(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
        request(path, HttpMethod.POST, httpEntity, false);
    }

    public void toFile(@Nullable ResponseEntity<String> response) throws IOException {
        assert response != null;
        HttpHeaders headers     = response.getHeaders();
        MediaType   contentType = headers.getContentType();
        assert contentType != null;
        String subtype = contentType.getSubtype();
        String suffix  = ".txt";
        if (subtype.contains("json")) {
            suffix = ".json";
        }
        byte[]           bodyBytes = Objects.requireNonNull(response.getBody()).getBytes(StandardCharsets.UTF_8);
        String           userHome  = System.getProperty("user.home");
        SimpleDateFormat sdf       = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String           filePath  = userHome + "/.cache/apitest/" + sdf.format(new Date()) + suffix;

        Path fd = Paths.get(filePath);
        if (!Files.exists(fd)) Files.createFile(fd);
        Files.write(fd, bodyBytes);
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println("filePath = " + filePath);
    }


    /**
     * TLS的三个作用：
     * （1）身份认证
     * 通过证书认证来确认对方的身份，防止中间人攻击
     * （2）数据私密性
     * 使用对称性密钥加密传输的数据，由于密钥只有客户端/服务端有，其他人无法窥探。
     * （3）数据完整性
     * 使用摘要算法对报文进行计算，收到消息后校验该值防止数据被篡改或丢失。
     * <p>
     * 使用RestTemplate进行HTTPS请求访问：
     * private static RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
     */
    public static class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {
        @Override
        protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
            try {
                if (!(connection instanceof HttpsURLConnection)) {
                    throw new RuntimeException("An instance of HttpsURLConnection is expected");
                }

                HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;

                TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }

                }};
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                httpsConnection.setSSLSocketFactory(new HttpsClientRequestFactory.MyCustomSSLSocketFactory(sslContext.getSocketFactory()));

                httpsConnection.setHostnameVerifier((s, sslSession) -> true);

                super.prepareConnection(httpsConnection, httpMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static class MyCustomSSLSocketFactory extends SSLSocketFactory {

            private final SSLSocketFactory delegate;

            public MyCustomSSLSocketFactory(SSLSocketFactory delegate) {
                this.delegate = delegate;
            }

            // 返回默认启用的密码套件。除非一个列表启用，对SSL连接的握手会使用这些密码套件。
            // 这些默认的服务的最低质量要求保密保护和服务器身份验证
            @Override
            public String[] getDefaultCipherSuites() {
                return delegate.getDefaultCipherSuites();
            }

            // 返回的密码套件可用于SSL连接启用的名字
            @Override
            public String[] getSupportedCipherSuites() {
                return delegate.getSupportedCipherSuites();
            }


            @Override
            public Socket createSocket(final Socket socket, final String host, final int port, final boolean autoClose) throws IOException {
                final Socket underlyingSocket = delegate.createSocket(socket, host, port, autoClose);
                return overrideProtocol(underlyingSocket);
            }


            @Override
            public Socket createSocket(final String host, final int port) throws IOException {
                final Socket underlyingSocket = delegate.createSocket(host, port);
                return overrideProtocol(underlyingSocket);
            }

            @Override
            public Socket createSocket(final String host, final int port, final InetAddress localAddress, final int localPort) throws IOException {
                final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
                return overrideProtocol(underlyingSocket);
            }

            @Override
            public Socket createSocket(final InetAddress host, final int port) throws IOException {
                final Socket underlyingSocket = delegate.createSocket(host, port);
                return overrideProtocol(underlyingSocket);
            }

            @Override
            public Socket createSocket(final InetAddress host, final int port, final InetAddress localAddress, final int localPort) throws IOException {
                final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
                return overrideProtocol(underlyingSocket);
            }

            private Socket overrideProtocol(final Socket socket) {
                if (!(socket instanceof SSLSocket)) {
                    throw new RuntimeException("An instance of SSLSocket is expected");
                }
                ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"});
                return socket;
            }
        }
    }
}
