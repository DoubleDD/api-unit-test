package com.example.springboot.http.甘肃.一张图接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 岸线功能分区 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_MA
    }

    @Test
    fun 岸线功能分区详情() {
        val projectType = 30
        val areaCode = "1240"
        get("/api/water/waterLine/queryByCode?projectType=$projectType&areaCode=$areaCode")
    }
}