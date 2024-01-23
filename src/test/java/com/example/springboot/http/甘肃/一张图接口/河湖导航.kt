package com.example.springboot.http.甘肃.一张图接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 河湖导航 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_MA
    }


    @Test
    fun list() {
        val path = "/api/riverAndLake/nav/list"
        get(path)
    }
}