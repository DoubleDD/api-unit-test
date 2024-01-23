package com.example.springboot.http.甘肃.cases

import com.alibaba.fastjson.JSONObject
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import java.io.File
import java.net.URLEncoder

class 短信接口测试 : BaseTest() {

    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_THIRD
//        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.PROD_THIRD
    }

    override fun getToken(): String {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MTM4LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiYTRhNWU5NmM2MzliNDllYTk5OWFjNDQ0MTcwMjhkMjAiLCJpYXQiOjE2NDI0MjY5MjksInVzZXJuYW1lIjoia2Vkb25nMSJ9.olQbs5kbnwV2nksHO6rr4aJD3ek0yGXRxz8quP5w2ME"
    }


    private fun readFile(filename: String): String {
        val canonicalPath = File("").canonicalPath
        val filePath = "$canonicalPath/src/test/java/$filename"
        val message = File(filePath).readText()
        print(message)
        return message
    }

    @Test
    fun sms() {
        val path = "/sms/send/message"
        val mobile = "13277918809"
//        val mobile = "18620738640"
        val content = URLEncoder.encode("【甘肃水保】测试短信——甘肃项目技术", "utf-8")
        readFile("com/example/springboot/http/甘肃/cases/短信.txt")

        val statisticFlag = true
        val body = "mobile=$mobile&content=$content&statisticFlag=$statisticFlag"
        val header = HttpHeaders()
        header["Content-Type"] = "application/x-www-form-urlencoded"
        // 视频集控
        appKeySecretPair.set(Pair("375c36ac3c134562829ce017025489ff--", "8f5e685fd5284abf9b106c970c152442"))
        // 山洪灾害
//        appKey:     ee09b9d29a194e059abd19e3e60de226
//        appSecret:  7edba91bfbcb425a9db5eb68c19fc8d7c769ef74dcda4b59bb177e5aa18a8715
        appKeySecretPair.set(
            Pair(
                "ee09b9d29a194e059abd19e3e60de226",
                "7edba91bfbcb425a9db5eb68c19fc8d7c769ef74dcda4b59bb177e5aa18a8715"
            )
        )
        request(path, HttpMethod.POST, HttpEntity(body, header))
    }

    @Test
    fun smsV2() {
        val path = "/sms/send/message/v2"
        val mobile = "13277918809"
//        val mobile = "18620738640"
        val content = URLEncoder.encode("【甘肃水保】测试短信——甘肃项目技术", "utf-8")
        readFile("com/example/springboot/http/甘肃/cases/短信.txt")

        val statisticFlag = true
        val body = "mobile=$mobile&content=$content&statisticFlag=$statisticFlag"
        val header = HttpHeaders()
        header["Content-Type"] = "application/x-www-form-urlencoded"
        // 视频集控
        appKeySecretPair.set(Pair("375c36ac3c134562829ce017025489ff--", "8f5e685fd5284abf9b106c970c152442"))
        // 山洪灾害
//        appKey:     ee09b9d29a194e059abd19e3e60de226
//        appSecret:  7edba91bfbcb425a9db5eb68c19fc8d7c769ef74dcda4b59bb177e5aa18a8715
        appKeySecretPair.set(
            Pair(
                "ee09b9d29a194e059abd19e3e60de226",
                "7edba91bfbcb425a9db5eb68c19fc8d7c769ef74dcda4b59bb177e5aa18a8715"
            )
        )
        request(path, HttpMethod.POST, HttpEntity(body, header))
    }

    @Test
    fun smsV3() {
        val path = "/sms/send/message/v3"
        val mobile = "13277918809"
//        val mobile = "18620738640"
        val content = URLEncoder.encode("【甘肃水保】测试短信——甘肃项目技术", "utf-8")
        readFile("com/example/springboot/http/甘肃/cases/短信.txt")

        val body = "mobile=$mobile&content=$content"
        val header = HttpHeaders()
        header["Content-Type"] = "application/x-www-form-urlencoded"
        // 视频集控
        appKeySecretPair.set(Pair("375c36ac3c134562829ce017025489ff--fahklsdf", "8f5e685fd5284abf9b106c970c152442"))

        // 山洪灾害
//        appKey:     ee09b9d29a194e059abd19e3e60de226
//        appSecret:  7edba91bfbcb425a9db5eb68c19fc8d7c769ef74dcda4b59bb177e5aa18a8715
        request(path, HttpMethod.POST, HttpEntity(body, header))
    }

    @Test
    fun 获取短信回执() {
        val mobile = "13277918809"
        val serialNumber = "78366851660707156601"
        val path = "/sms/report?serialNumber=$serialNumber"

        get(path)
    }

    override fun withFeign(): Boolean {
        return false
    }
}

class 大平台接口 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.DEV_PORTAL
//        return ServerApi.PRE_PROD_PORTAL
        return ServerApi.LOCAL_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MjY0LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNjVkZmY5MmM1YjMxNDQwMzk0YzM2ZmRkMjg3OWZmZTciLCJpYXQiOjE2OTAwOTc5MTksInVzZXJuYW1lIjoia2Vkb25nMSJ9.hspD3h41KpnJ7kwvDGVB6g25_Ep70I1uT3n8wG2ZApk
        """.trimIndent()
    }

    @Test
    fun statistic() {
        val path = "/api/sms/statistic"
        val gid = "d8828534b9ad4467a0c12c7daa214db7"
        val userId = "94f3962a638966037ffa9f7d7c26cdc3"
        val body = "gid=$gid&userId=$userId"
        val header = HttpHeaders()
        header["Content-Type"] = "application/x-www-form-urlencoded"

        request(path, HttpMethod.POST, HttpEntity(body, header))
    }

    @Test
    fun sendMsg() {
        val path = "/api/sms/send"
        val bodyMap = HashMap<String, Any>()
        val recvTels = ArrayList<String>()
        recvTels.add("13277918809")
        bodyMap["recvTels"] = recvTels
        bodyMap["content"] = "统计测试"
        bodyMap["isReply"] = false
        bodyMap["statisticFlag"] = false
        bodyMap["sendTime"] = ""
        val header = HttpHeaders()
        header["Content-Type"] = "application/json"

        request(path, HttpMethod.POST, HttpEntity(JSONObject.toJSONString(bodyMap), header))
    }

    @Test
    fun cancel() {
        val gid = "a7fea55ad26846abbf653c6ae53b5ff1"
        val path = "/api/sms/cancel?gid=$gid"
        get(path)
    }

    @Test
    fun 详情() {
        formatJson.set(true)
        val gid = "11e4aa755d3d4b26b40bde5e9a84631a"
        get("/api/sms/info?gid=$gid")
    }

    @Test
    fun 导出() {
        val gid = "401b30978f4644b29363d747298d5f20"
        val path = "/api/sms/info/export?gid=$gid"
        get(path)
    }

    @Test
    fun 部门的短信列表() {
        val pageSize = 10
        val pageNum = 1
        val isReply = false
        val startTime = URLEncoder.encode("2023-07-20 00:00:00")
        val endTime = URLEncoder.encode("2023-07-30 00:00:00")
        val content = ""
        get("/api/sms/list/dept?pageSize=$pageSize&pageNum=$pageNum&isReply=$isReply&startTime=$startTime&endTime=$endTime&content=$content")
    }


}