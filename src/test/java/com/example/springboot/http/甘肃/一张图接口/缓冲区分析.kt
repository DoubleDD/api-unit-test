package com.example.springboot.http.甘肃.一张图接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import java.io.File

class 缓冲区分析 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_MA
        return ServerApi.DEV_MA
    }

    private val riverId = "123123123123"
    private val type = "RES"
    private val m = 800

    @Test
    fun 缓冲区查询() {
        val path = "/buffer/analysis/start?riverId=$riverId&type=$type&m=$m"
        val body = File("./src/test/java/com/example/springboot/http/一张图接口/linestring.json").readText()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 缓冲区查询结果统计() {
        val path = "/buffer/analysis/result/statistic?riverId=$riverId&types=$type&m=$m"
        formatJson.set(true)
        get(path)
    }


    @Test
    fun 缓冲区查询结果列表() {
        val path = "/buffer/analysis/result/list?riverId=$riverId&types=$type&m=$m"
        formatJson.set(true)
        get(path)
    }

    @Test
    fun 水资源开发与利用() {
        val path = "/api/over/the/year/getOverTheYearRainfallData"
        formatJson.set(true)
        get(path)
    }


}