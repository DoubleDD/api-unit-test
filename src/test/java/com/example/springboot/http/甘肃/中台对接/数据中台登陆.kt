package com.example.springboot.http.甘肃.中台对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType


class 数据中台登陆 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_THIRD
        return ServerApi.PRE_PROD_THIRD
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6ODY3LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiYTUzMzQxZjk3ZmRiNDIzM2I4YTY1YjBjOGJiY2JjZDYiLCJpYXQiOjE2ODkyMzQ3MTEsInVzZXJuYW1lIjoia2Vkb25nMSJ9.icmeVNh1FKAG27gG7MewJiHrVRvllyaeJQo2t62ykwo
        """.trimIndent()
    }

    @Test
    fun 单点() {
        val type = 2
        val path = "/api/data/center/index?type=$type&token=$token"
        var get = get(path)
        var headers = get.headers
        println("响应头：")
        for (header in headers) {
            println("${header.key}=${header.value}")
        }
    }

    @Test
    fun 同步数据中台密码() {
        val path = "/api/data/center/syncPwd/gs_epc"
        val userId = "9accb1e3c10c88130335e0ea06fe3d9b"
//        val userId = "94f3962a638966037ffa9f7d7c26cdc3"
        val password = "12345678"
        post(path, "userId=$userId&password=$password", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 同步应用中台密码() {
        val path = "/api/data/center/syncPwd"
        val userId = "94f3962a638966037ffa9f7d7c26cdc3"
        val password = "12345678"
        post(path, "userId=$userId&password=$password", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 同步应用中台密码2() {
        val path = "/api/data/center/syncPwd/mid_app"
        val userId = "94f3962a638966037ffa9f7d7c26cdc3"
        val password = "12345678"
        post(path, "userId=$userId&password=$password", MediaType.APPLICATION_FORM_URLENCODED)
    }
}