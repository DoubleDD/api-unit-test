package com.example.springboot.http.分水江.预警

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.net.URLEncoder

class 雨情预警 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_FSJ_SMART_RIVER
    }


    @Test
    fun 雨量列表() {
        val type = "ZQ,ZZ,TT,DD"
        val startTime = URLEncoder.encode("2022-11-28 18:00:00")
        val endTime = URLEncoder.encode("2022-11-30 18:00:00")
        formatJson.set(true)
        get("/rainfall/v2/warning/list?type=$type&pageNum=1&pageSize=10&startTime=$startTime&endTime=$endTime")
    }

    @Test
    fun 统计() {
        val type = "ZQ,ZZ,TT,DD"
        val startTime = URLEncoder.encode("2022-11-28 18:00:00")
        val endTime = URLEncoder.encode("2022-11-30 18:00:00")
        formatJson.set(true)
        get("/rainfall/v2/statistic?type=$type&pageNum=1&pageSize=10&startTime=$startTime&endTime=$endTime")
    }
}