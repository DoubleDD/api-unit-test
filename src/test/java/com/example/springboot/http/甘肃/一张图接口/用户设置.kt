package com.example.springboot.http.甘肃.一张图接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 用户设置 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_MA
    }


    @Test
    fun 增加设置() {
        val path = "/api/user/config"
        @Language("JSON")
        val body = """
            {
              "id": "123",
              "configKey": "opLayer",
              "model": "2",
              "configValue": "sanwei-shiliangtu",
              "userId": "94f3962a638966037ffa9f7d7c26cdc31"
            }
        """.trimIndent()
        post(path,body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 查询设置() {
        val path = "/api/water/industry/type/list"
        get(path)
    }
}