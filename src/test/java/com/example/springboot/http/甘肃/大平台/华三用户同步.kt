package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 华三用户同步 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_PORTAL
    }


    @Test
    fun add() {
        @Language("JSON")
        val body = """
            {
              "userName": "douhanyang",
              "realname": "陡晗阳",
              "deptId": "8d99d7954a344a7487e3fc50c94f94ca",
              "gender": 1
            }
        """.trimIndent()
        post("/syncUser/add", body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun update() {
        @Language("JSON")
        val body = """
            {
              "userName": "douhanyang",
              "realname": "陡晗阳",
              "deptId": "8d99d7954a344a7487e3fc50c94f94ca",
              "gender": 1
            } 
        """.trimIndent()

        post("/syncUser/edit", body, MediaType.APPLICATION_JSON)
    }
}