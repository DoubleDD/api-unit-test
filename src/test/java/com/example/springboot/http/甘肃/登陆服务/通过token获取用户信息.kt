package com.example.springboot.http.甘肃.登陆服务

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import com.example.springboot.http.common.SignUtil
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import java.util.*
import kotlin.test.assertEquals


class 通过token获取用户信息 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6ODczLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiMjViMDAzNmJlZDQ0NDQ4MGFhMTA0Njg5NGRhMTMzZTMiLCJpYXQiOjE2OTEzNzMxNzEsInVzZXJuYW1lIjoia2Vkb25nMSJ9.AnqWw14AXu_VJPXFPMv1YHvan4OBLxpfQ3HUGT81RzM
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJmODExY2M4NjNlOWY0N2U2Yjk2MjIxOWRiMjhjY2JhYyIsInJhbmRvbSI6NDQ5LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiNDY3NzNhYWFhN2E0ZmUwMGJiZjkxNWRjMzFkOGQzYWQiLCJ1dUlkIjoiMmIwOTRmYTk3ZWMyNGQwMjliNmZmYWM5OWJmZjJiOGEiLCJ1c2VybmFtZSI6ImFkbWluMSJ9.LNbgVIr3OdgEwGexQ7GH1kYFqoP5yTckDzfOXQ7KYeQ
        """.trimIndent()

    }

    @Test
    fun 获取用户信息() {
//        val env = Env.DEV
        val env = Env.PROD
        val path = "${env.server}/yunli/transport/v1/transport/UC_USER_INFO_BY_TOKEN?token=$token"

        val timestamp = System.currentTimeMillis()
        val sign = getSign(env, timestamp)

        val header = HttpHeaders()
        header["clientId"] = env.clientId
        header["timestamp"] = "$timestamp"
        header["sign"] = sign
        header["Content-Type"] = null

        formatJson.set(true)
        request(path, HttpMethod.POST, HttpEntity(header))

    }

    @Test
    fun 签名计算() {
        val timestamp = 1665655399738
        val sign = getSign(Env.PROD, timestamp)
        assertEquals("Da3HJw5gxS5NnElZ7BRxpHRK+8BAszp99oNC4KU0zJE=", sign)

    }

    private fun getSign(env: Env, timestamp: Long): String {
        val action = "UC_USER_INFO_BY_TOKEN"
        val map: MutableMap<String, String> = TreeMap()
        map["token"] = token
        val sign = SignUtil.sign(env.clientId, env.clientSecret, timestamp.toString() + "", action, map)
        println("\r\ntimestamp--->$timestamp\r\nsign---->$sign")
        return sign
    }
}

enum class Env(val server: String, val clientId: String, val clientSecret: String) {
    DEV("http://39.100.95.69:30102", "gansu_dev", "ZTAzZDYzOTYwZTBkNDBiYzk0Zjc1MzI5NDIwNDE0MDE="),
    PROD("http://60.13.54.71:30118", "gansu_eco", "YzAxNjgyYzQ0ZjNhNDc5M2IzNzEzMjRiYzNjYmUxMjQ="),
}