package com.example.springboot.http.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SignUtil {

    public static String sign(String clientId, String clientSecret, String timestamp, String action, Map<String, String> map) {
        Map<String, String> params = new HashMap<>(16);
        params.put("clientId", clientId);
        params.put("clientSecret", clientSecret);
        params.put("timestamp", timestamp);
        params.put("action", action);
        if (map != null && !map.isEmpty()) {
            params.putAll(map);
        }

        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert messageDigest != null;
        String data = buildParams(params) + clientSecret;
        System.out.println("原始数据：\r\n" + data);
        messageDigest.update(data.getBytes());
        byte[] digest = messageDigest.digest();
        return Base64.getEncoder().encodeToString(digest);
    }

    private static String buildParams(Map<String, String> parms) {
        StringBuilder sbf = new StringBuilder();
        if (parms != null && !parms.isEmpty()) {
            Map<String, String> resultMap = new TreeMap<>(String::compareTo);
            resultMap.putAll(parms);

            for (Entry<String, String> entry : resultMap.entrySet()) {
                if (!"sign".equals(entry.getKey())) {
                    sbf.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }
            if (sbf.length() > 0) {
                sbf.deleteCharAt(sbf.length() - 1);
            }
        }
        return sbf.toString();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        String              clientId     = "gansu_eco";
        String              clientSecret = "YzAxNjgyYzQ0ZjNhNDc5M2IzNzEzMjRiYzNjYmUxMjQ=";
        String              action       = "UC_USER_INFO_BY_TOKEN";
        long                timestamp    = 1665655399738L;
        Map<String, String> map          = new TreeMap<>();
        map.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJmNDg4NzhhZGI3MGQ0YWMzYWFkNTVlNGFiZGVhMjJkOSIsInJhbmRvbSI6MTQsImdyb3VwSWQiOiIwMTI0MTJkMmE3YzI0NjFlMTkzZjM2NzYzM2YxM2MxOCIsImFwcElkIjoiZ2Fuc3VfYXBwbHkiLCJ1c2VySWQiOiIzNWIwOGM5N2M1OTE4ZDRhYzViYjZkY2Q0MjA5YTgwYSIsInV1SWQiOiIyNTI5MWE2NzQyN2Q0NTg5YTkyNmNlMjVlMTMyZDM0MCIsImlhdCI6MTY2NTY1MjAxMywidXNlcm5hbWUiOiJEYXNodSJ9.9EKAxH6spoYVYEyTVLEYozE5lknZzRkRq3L1wnO97Yo");
        String sign = SignUtil.sign(clientId, clientSecret, timestamp + "", action, map);

        System.out.println("sign---->" + sign);

        // 校验签名正确性
        System.out.println("Da3HJw5gxS5NnElZ7BRxpHRK+8BAszp99oNC4KU0zJE=".equals(sign));
    }
}

