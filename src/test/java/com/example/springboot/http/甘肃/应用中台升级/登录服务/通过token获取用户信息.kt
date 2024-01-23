package com.example.springboot.http.甘肃.应用中台升级.登录服务

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import com.example.springboot.http.common.SignUtil
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import java.util.*


class 通过token获取用户信息 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    override fun getToken(): String {
//        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6Mzg0LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiMWJkZjU4MTNiMzZkNGIwOGExODA5OWRiZTY4YjYyZWYiLCJpYXQiOjE2NDI1NTkyOTQsInVzZXJuYW1lIjoia2Vkb25nMSJ9.d7515vSlGK0lHP3tzcx7kHPZLb4QL-ibOpGoB_u8yzk"
        return "eyJraWQiOiIyMDIxMDUwODEzNDE0MTg0MDU4NDI0NjI4MzM0MTgyNCIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2Iiwic2lkIjoiNDY1Nzk4YjNiMmVjNDg2YThlNTFlODgwYmNmMzg0ZDgifQ.eyJyb2xlSWRzIjpbIjIwMjEwNDI3MTgwNjA4ODM2NjY0NTMwNDg1NTE0MjQwIl0sIm9yZ0lkcyI6W10sIm5zIjoiZGVmYXVsdCIsImxvZ2luVHlwZSI6MCwicmVxdWVzdElkIjoiZjYwMTU1NjEtMTcyNDEiLCJsb2dpbkF0IjoxNjk5NDk0MDUzMjgwLCJ1c2VybmFtZSI6ImFkbWluIn0.h34HxmQiqXFoGwFsSgXpDuhTk2d7YJ-B4ezPDQBPzrY"

    }

    @Test
    fun 获取用户信息() {
//        val env = Env.DEV
//        val env = Env.PROD
        val env = Env.FSJ
        val path = "${env.server}/transport/v1/transport/UC_USER_INFO_BY_TOKEN?token="

        val timestamp = System.currentTimeMillis()
        val sign = getSign(env, timestamp)

        val header = HttpHeaders()
//        header["clientId"] = env.clientId
//        header["timestamp"] = "$timestamp"
//        header["sign"] = sign
//        header["Content-Type"] = null

        request(path + token, HttpMethod.POST, HttpEntity(header))

    }

    private fun getSign(env: Env, timestamp: Long): String {
        val action = "UC_USER_INFO_BY_TOKEN"
        val map: MutableMap<String, String> = TreeMap()
        map["token"] = token
        val sign = SignUtil.sign(env.clientId, env.clientSecret, timestamp.toString() + "", action, map)
        println("timestamp--->$timestamp  sign---->$sign")
        return sign
    }
}

enum class Env(val server: String, val clientId: String, val clientSecret: String) {
    DEV("http://39.100.95.69:30102/yunli", "gansu_dev", "ZTAzZDYzOTYwZTBkNDBiYzk0Zjc1MzI5NDIwNDE0MDE="),
    PROD("http://60.13.54.71:30118/yunli", "gansu_eco", "YzAxNjgyYzQ0ZjNhNDc5M2IzNzEzMjRiYzNjYmUxMjQ="),
    FSJ("http://172.30.1.159:32322", "gansu_eco", "YzAxNjgyYzQ0ZjNhNDc5M2IzNzEzMjRiYzNjYmUxMjQ="),
}