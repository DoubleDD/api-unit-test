package com.example.springboot.http.甘肃.登陆服务

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

class 扫码登录 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.DEV_UN
//        return ServerApi.LOCAL_UN
    }


    override fun getToken(): String? {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MTY3LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiODY5NGU3YWFlMDlkNDgyOGFkNzc4ZmUwMjJlYzQ3MWIiLCJpYXQiOjE2ODQxMzUwNzgsInVzZXJuYW1lIjoia2Vkb25nMSJ9.8e4i0Uxv1pDWEFlEP4sfg0__8gGcTDWNMC_sBFoelUI
        """.trimIndent()
    }

    @Test
    fun 登录() {
        val path = "/uniauth/qrcode/bind"
//        val gid = "RCE_LOGIN@5ZCsnfTlQjknQ2AVVnFf_0@windows"
//        val type = 1
        val type = 0
        val gid = "fa0eb1b209db4f919c2009c046ee5c93"
        val headers = HttpHeaders()
        headers["Content-Type"] = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        request(path, HttpMethod.POST, HttpEntity("gid=$gid&type=$type&token=$token", headers))
    }
}