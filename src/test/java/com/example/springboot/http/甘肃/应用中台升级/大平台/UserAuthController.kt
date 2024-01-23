package com.example.springboot.http.甘肃.应用中台升级.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod

class UserAuthController : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.UPDATE_PORTAL
//        return ServerApi.PROD_PORTAL
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.DEV_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6Mjk4LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNmQ1ZWI3ODc1ZjI4NDk1M2I2MTEwOWU1ZDMwMjMzZjUiLCJpYXQiOjE2ODQ4OTUzMTQsInVzZXJuYW1lIjoia2Vkb25nMSJ9.PCQfVmVHBPPtTrlaHCx--Db78okGOY1R36tSkQL8P5E
        """.trimIndent()
    }


    @Test
    fun 头部菜单() {
        formatJson.set(true)
        get("/api/auth/headers")
    }

    @Test
    fun 查询权限() {
        val header = HttpHeaders()
//        header["user-agent"] = "dart:io"
        header["uri"] = "#/home"
        request("/api/auth/listAuth", HttpMethod.GET, HttpEntity(header))
    }
    @Test
    fun app查询权限() {
        val header = HttpHeaders()
        header["user-agent"] = "dart:io"
//        header["uri"] = "#/home"
        request("/api/auth/listAuth/app", HttpMethod.GET, HttpEntity(header))
    }


}