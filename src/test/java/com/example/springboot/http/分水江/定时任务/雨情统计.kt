package com.example.springboot.http.分水江.定时任务

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.net.URLEncoder

class 雨情统计 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_FSJ_DATA_TOOLKIT
    }

    @Test
    fun 测站雨情统计() {
        val startTime = URLEncoder.encode("2022-11-23 00:00:00")
        val endTime = URLEncoder.encode("2022-11-30 00:00:00")
        val codes = "70115476"
        get("/warning/rainfall/n?startTime=$startTime&endTime=$endTime&codes=$codes")
    }

    @Test
    fun 面雨量统计() {
        val startTime = URLEncoder.encode("2017-06-23 00:00:00")
        val endTime = URLEncoder.encode("2017-06-26 00:00:00")
        val codes = "70115080"
        get("/warning/rainfall/area?startTime=$startTime&endTime=$endTime&codes=$codes")
    }
}