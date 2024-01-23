package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

class 字典 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_PORTAL
//        return ServerApi.DEV_PORTAL
//        return ServerApi.LOCAL_PORTAL
    }

    @Test
    fun clear() {
        val path = "/dict/clear/dict_items"
        get(path)
    }

    val token1 = """
        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NDI1LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNjliZThlYjFmZDYzNGM4YWI5ZWJiMzBjM2EyNmJiZjkiLCJpYXQiOjE2ODg0MzU2MTMsInVzZXJuYW1lIjoia2Vkb25nMSJ9.9RyrIlOSsug9M-NLNmzSBZKYfLk520r8T23WZDZkF_k
    """.trimIndent()

    @Test
    fun ios() {
        var dictName = "APP_STORE_USER"
        dictName = "dpt_index_rest"
        dictName = "app_transform"
        dictName = "iot_shuiku_data_type"
        val path = "/dict/map?dictName=${dictName}"
        val header = HttpHeaders()
        header.add(HttpHeaders.COOKIE, "token=$token1")
        formatJson.set(true)
        get(path, header)
    }

    override fun withFeign(): Boolean {
        return false
    }

    @Test
    fun statistic() {
        val path = "/api/service/use/statistic/detail?tag=report_component"
        val header = HttpHeaders()
        header.add(HttpHeaders.COOKIE, "token=$token1")
        get(path, header)
    }
}