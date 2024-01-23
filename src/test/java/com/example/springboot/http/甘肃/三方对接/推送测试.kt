package com.example.springboot.http.甘肃.三方对接

import com.alibaba.fastjson.JSONObject
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import java.net.URLDecoder
import java.net.URLEncoder

class 推送测试 : BaseTest() {

    override fun getServer(): ServerApi {
//        return ServerApi.PRE_PROD_THIRD
        return ServerApi.PROD_THIRD
//        return ServerApi.DEV_THIRD
//        return ServerApi.LOCAL_THIRD
    }

    /*

    {"audience":{"cid":["0a2f8ce415b00c84ba3b8ef414f338fb"]},"push_channel":{"android":{"ups":{"notification":{"click_type":"startapp","title":"测试消息推送1119","body":"测试消息推送1119","url":"url"}}},"ios":{"payload":"{\"title\":\"测试消息推送1119\",\"body\":\"测试消息推送1119\"}","aps":{"alert":{"title":"测试消息推送1119","body":"测试消息推送1119"},"sound":"com.gexin.ios.silence","content-available":0,"category":"ACTIONABLE"},"auto_badge":"+1","type":"notify"}},"push_message":{"notification":{"body":"测试消息推送1119","click_type":"payload","payload":"{\"data\":\"{\\\"url\\\":\\\"http://60.13.54.71:30119/oa/openwork/message_info.jsp?requestid=600728&loginid={loginid}\\\"}\",\"title\":\"测试消息推送1119\",\"body\":\"测试消息推送1119\"}","title":"测试消息推送1119","url":"http://60.13.54.71:30119/oa/openwork/message_info.jsp?requestid=600728&loginid={loginid}"}},"request_id":"90826005808353073836622994997258","settings":{"ttl":3600000}}


 @Value("${gt.url:}")
    private String gtUrl;//="https://restapi.getui.com/v2/kEyJvDjMzv5fx1xCii2y8A";
    @Value("${gt.appKey:}")
    private String appKey;//="Xz4h4NLU0U69F0LoWqt5e";
    @Value("${gt.masterSecret:}")
    private String masterSecret;//="shSANUGzV47tCBhkJbFkS1";


个推
fengsheng
id:edcd03b4a319472c25848cebbbae1db8
     */

    @Test
    fun push() {
        formatJson.set(true)
        var url = "/push/"
        val map = HashMap<String, String>()
        map["title"] = "会议提醒4"
        map["content"] = "2023-07-01早上9点30分，于二楼会议室开会。"
//        map["cid"] = "8cb8ecea3b30dd63c1a162f89687561a"
//        map["cid"] = "9b4e2d9e4e93c33c9924f6cabaf52426"
//        map["cid"] = "8e3be16d6014b9e6e86a6a25d83d9fba"
//        map["cid"] = "5221cc416419500f1a65a283c1899319"
//        map["cid"] = "0531c6593aeda88c7081f741b576e2b9"
//        map["cid"] = "87cf5f0bfc9830c19cfcf3a719e48bfc"
//        map["cid"] = "3e17b51798740c133d8601683618f44c"
//        map["cid"] = "edcd03b4a319472c25848cebbbae1db8"

        // 荣耀
//        map["cid"] = "02b720b0852473aa68a72a46bb3bb615"

        val json = JSONObject()
//        json["url"] = "http://www.baidu.com"
        map["payload"] = json.toJSONString()
        map["username"] = "kedong1"
        var qs = "?"
        for (entry in map) {
            qs = qs + entry.key + "=" + URLEncoder.encode(entry.value) + "&"
        }
        url += qs
        request(url, HttpMethod.GET, null)
    }

    @Test
    fun encodeTest() {
        val json = JSONObject()
        json["url"] = "http://60.13.54.71:30119/oa/openwork/message_info.jsp?requestid=588694&loginid={loginid}"

        val qs = URLEncoder.encode(json.toJSONString())
        println(qs)

        val sq = URLDecoder.decode(qs)
        println(sq)
        val parse = JSONObject.parse(sq)
        println(parse)
    }
}