package com.example.springboot.正则测试;

import org.junit.jupiter.api.Test;

public class 替换 {
    @Test
    void replace(){
        String url1 = "http://39.100.95.69:30101/minio/assessment/third/微信图片_2022061310055410.jpg ";
        String url2 = "http://39.100.95.69:30102/oss/assessment/third/微信图片_2022061310055410.jpg ";
        String reg = "http://39.100.95.69:(30101|30102)/(oss|minio)";
        url1 = url1.replaceAll(reg, "");
        url2 = url2.replaceAll(reg, "");
        assert url1.equals(url2);
    }
}
