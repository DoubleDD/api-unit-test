package com.example.springboot.http.分水江.大屏

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 一本帐 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_FSJ_8080
    }

    @Test
    fun 取用水信息() {
        formatJson.set(true)
        val name = "全市"
        val year = 2022
        get("/ybz/water/use?name=$name&year=$year")
    }
}