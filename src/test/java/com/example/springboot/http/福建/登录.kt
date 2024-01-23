package com.example.springboot.http.福建

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import java.util.*

class 登录 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.DEV_FUJIAN_NEW_JAVA
        return ServerApi.LOCAL_FUJIAN_NEW_JAVA
    }


    @Test
    fun app登录() {
        var path = "/api/auth/app/login"
//        path = "/newAPI/users/v2.0/login"

        val username = "xtzc02"
        var password = "SPTer@#0901"
        password = Base64.getEncoder().encodeToString(password.toByteArray());
        val body = """
            {"appType":"android","loginType":0,"password":"$password","username":"$username"}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }


}


