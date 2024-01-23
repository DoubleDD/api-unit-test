package com.example.springboot.http.common;

import static com.example.springboot.http.common.AppConfig.*;

public enum ServerApi {

    DEV_MA("http://172.30.1.212:30101/yunli/ma/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_APP("http://172.30.1.212:30101/yunli/app/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_THIRD("http://172.30.1.212:30101/yunli/third/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_THIRD_DATA("http://172.30.1.212:30101/yunli/third-data/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_PORTAL("http://172.30.1.212:30101/yunli/portal/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_UN("http://172.30.1.212:30101/yunli/un/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_AD("http://172.30.1.212:30102/yunli/ad/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_SR("http://172.30.1.212:30102/yunli/sr/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_FS("http://172.30.1.211:30321", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_UN_UPDATE("http://172.30.1.212:31696", false, DEV_APPKEY, DEV_APP_SECRET),
    DEV_YEARBOOK("http://172.30.1.212:30101/yunli/yearbook", false, DEV_APPKEY, DEV_APP_SECRET),
    INNER_MESSAGE("http://172.30.1.212:31560", false, DEV_APPKEY, DEV_APP_SECRET),
    INNER_DEV("http://172.30.1.212:30108", true, DEV_APPKEY, DEV_APP_SECRET),
    INNER_PORTAL("http://172.30.1.212:31193", true, DEV_APPKEY, DEV_APP_SECRET),

    DEV_HUIZHOU_PUB("http://172.30.1.106:7000/yunli/p/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    DEV_MEIZHOU_USER("http://172.26.30.176:32660/yunli/user/v1", false, PROD_APPKEY, PROD_APP_SECRET),


    LOCAL_FS("http://localhost:9090", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_THIRD("http://localhost:9090", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_THIRD_DATA("http://localhost:9099", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_MA("http://localhost:9091", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_YEARBOOK("http://localhost:9093", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_PORTAL("http://localhost:9092", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_APP("http://localhost:9090", false, DEV_APPKEY, DEV_APP_SECRET),
    UPDATE_PORTAL("http://172.30.1.212:30102/yunli/portal/v2", false, DEV_APPKEY, DEV_APP_SECRET),
    UPDATE_UN("http://172.30.1.212:30101/yunli/un/v2", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_AD("http://localhost:19006", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_UN("http://localhost:19101", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_SR("http://localhost:9091", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_MESSAGE("http://localhost:8080", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_HUIZHOU_USER("http://localhost:8001", false, DEV_APPKEY, DEV_APP_SECRET),
    LOCAL_HUIZHOU_PUB("http://localhost:8003", false, PROD_APPKEY, PROD_APP_SECRET),

    LOCAL_FUJIAN_NEW_JAVA("http://localhost:8080", false, PROD_APPKEY, PROD_APP_SECRET),
    DEV_FUJIAN_NEW_JAVA("http://112.111.16.135:31001", false, PROD_APPKEY, PROD_APP_SECRET),


    NONE("", false, "", ""),
    PRE_PROD_PORTAL("http://60.13.54.71:30119/yunli2/portal/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PRE_PROD_THIRD("http://60.13.54.71:30119/yunli2/third/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PRE_PROD_AD("http://60.13.54.71:30119/yunli2/ad/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_PORTAL("http://60.13.54.71:30118/yunli/portal/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_ONE_MAP("http://60.13.54.71:30119/yunli/ma/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_AD("http://60.13.54.71:30119/yunli/ad/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PRE_PROD_FS("http://60.13.54.71:30119/yunli2/fts/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_FS("http://60.13.54.71:30119/yunli/fts/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_THIRD("http://60.13.54.71:30118/yunli/third/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_APP("http://60.13.54.71:30119/yunli/app/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PRE_PROD_APP("http://60.13.54.71:30119/yunli2/app/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_THIRD_HTTPS("https://60.13.54.71:33238/yunli/third/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_UN("http://60.13.54.71:30119/yunli/un/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PRE_PROD_UN("http://60.13.54.71:30119/yunli/un/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PRE_PROD_SR("http://60.13.54.71:30119/yunli/sruav/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_UN2("http://60.13.54.71:30119/yunli2/un/v1", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_YEARBOOK("http://60.13.54.71:30119/yunli/yearbook", false, PROD_APPKEY, PROD_APP_SECRET),
    PROD_THIRD_DATA("http://60.13.54.71:30119/yunli/third-data/v1", false, DEV_APPKEY, DEV_APP_SECRET),
    PROD_THIRD_DATA_HTTPS("https://60.13.54.71:33238/yunli/third-data/v1", false, DEV_APPKEY, DEV_APP_SECRET),


    PROD_HUIZHOU_USER("http://113.96.111.117:7000/yunli/u/v1", false, PROD_APPKEY, PROD_APP_SECRET),


    /*
    ---------------------------------- 分水江----------------------
     */
    LOCAL_FSJ_DATA_TOOLKIT("http://localhost:8080/data-toolkit"),
    LOCAL_FSJ_8080("http://localhost:8080"),

    LOCAL_FSJ_SMART_RIVER("http://localhost:8080/reservoir"),
    DEV_FSJ_SMART_RIVER("http://172.30.1.159:32322/api"),



    LOCAL_ONEMAP_MANAGER("http://localhost:8084"),
    LOCAL_ONEMAP_SEARCH("http://localhost:9090"),


    SEATUNNEL_LOCAL("http://localhost:5801"),
    ;


    private final String  server;
    private final boolean isProxy;
    private final String  appKey;
    private final String  secret;


    ServerApi(String server) {
        this.server  = server;
        this.isProxy = false;
        this.appKey  = "";
        this.secret  = "";
    }

    ServerApi(String server, boolean isProxy, String appKey, String secret) {
        this.server  = server;
        this.isProxy = isProxy;
        this.appKey  = appKey;
        this.secret  = secret;
    }

    public static String build(ServerApi serverApi, String path) {
        String url = path;
        if (!ServerApi.NONE.equals(serverApi)) {
            url = serverApi.getServer() + path;
        }
        System.out.println("\r\n====================================================================================\r\n");
        System.out.println(url);
        return url;
    }

    public String getServer() {
        return server;
    }

    public boolean isProxy() {
        return isProxy;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSecret() {
        return secret;
    }

}

class AppConfig {
    public static final String DEV_APPKEY     = "test";
    public static final String DEV_APP_SECRET = "hafjbvchuerjbbdhlksdhfajksdhf";

    public static final String PROD_APPKEY     = "d82b982ad9cf42acb8334cf16ab24842";
    public static final String PROD_APP_SECRET = "f4a2f1253c9d4c889d880fbf0ad7be74f18a2d880f500a5786b";


}