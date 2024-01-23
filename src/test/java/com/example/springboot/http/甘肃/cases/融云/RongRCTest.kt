package com.example.springboot.http.甘肃.cases.融云

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod

class RongRCTest : BaseTest() {

    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_PORTAL
    }

    @Test
    fun 鉴权测试() {

    }

    @Test
    fun im() {
        val url = "/api/im/message/private/publish.json"
        val header = HttpHeaders()
        header["Content-Type"] = "Application/x-www-form-urlencoded"
        val body = ""
        request(url, HttpMethod.POST, HttpEntity(body, header))
    }

    @Test
    fun 历史消息() {
        val url = "/api/im/message/history.json"
        val header = HttpHeaders()
        header["Content-Type"] = "Application/x-www-form-urlencoded"
        val body = "date=2014010101"
        request(url, HttpMethod.POST, HttpEntity(body, header))
    }

    @Test
    fun Token失效() {
        val url = "/api/im/user/token/expire.json"
        val header = HttpHeaders()
        header["Content-Type"] = "Application/x-www-form-urlencoded"
        val userId = "kedong1"
        val time = System.currentTimeMillis()
        request(url, HttpMethod.POST, HttpEntity("userId=$userId&time=$time", header))
    }
}