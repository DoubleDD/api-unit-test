package com.example.springboot.http.甘肃.一张图接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 取水井 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_MA
    }

    @Test
    fun 取水井详情() {
        val projectType = 2
        val wellCode = "620821000523"
        get("/api/water/waterWell/queryByCode?projectType=$projectType&wellCode=$wellCode")
    }
}