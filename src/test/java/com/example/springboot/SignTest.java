package com.example.springboot;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;


public class SignTest {

    public static void main(String[] args) {
        String appKey    = "sXWyM2N16cA1H3MFn9Ce89bocoVPfKytYB";
        String secret    = "JEAkd04caYfpIbHdBHsuFoYi3GsMwy9feLA64JKTRBI392xkKEOTH3WJx91";
        String timestamp = "1693281435138";
        String nonce     = "jfaldf78q732";
        String sign      = getSign(appKey, secret, timestamp, nonce);
        // 预期值 sign = 59a6e476b81f1b6eb17fada377ae33b3
        System.out.println("sign = " + sign);
        assert "59a6e476b81f1b6eb17fada377ae33b3".equals(sign);
    }

    private static String getSign(String appKey, String secret, String timestamp, String nonce) {
        String data = secret +
                      Optional.ofNullable(appKey).orElse("") +
                      Optional.ofNullable(timestamp).orElse("") +
                      Optional.ofNullable(nonce).orElse("") +
                      secret;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(data.getBytes(StandardCharsets.UTF_8));
            byte[]        digest = messageDigest.digest();
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
            }
            String sign = result.toString();
            return sign;
        } catch (Exception e) {
            System.out.println("sign 计算异常" + e);
        }
        return "";
    }
}
