package com.example.springboot.http.甘肃.一张图接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 水利行业 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_MA
    }


    @Test
    fun list() {
        val name = ""
        val type = "1"
        val pageNum = 1
        val pageSize = 15
        val path = "/api/water/industry/list?name=$name&type=$type&pageNum=$pageNum&pageSize=$pageSize"
        get(path)
    }

    @Test
    fun typeList() {
        val path = "/api/water/industry/type/list"
        get(path)
    }
}