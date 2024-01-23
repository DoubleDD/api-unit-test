package com.example.springboot.http.甘肃.一张图接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.net.URLEncoder

class 河湖长信息 : BaseTest() {
    override fun getServer(): ServerApi {
       return ServerApi.LOCAL_MA
//        return ServerApi.DEV_MA
    }


    @Test
    fun 河湖长基础信息统计() {
        val regionCode = "620000"
        val path = "/api/special/river/statistic?regionCode=$regionCode"
        get(path)

    }


    @Test
    fun 雨情测站() {
        val cityRegionCode = "620000000000"
        val districtRegionCode = "620000000000"
        val startTime = URLEncoder.encode("2022-04-18 08:00:00")
        val endTime = URLEncoder.encode("2022-05-19 08:00:00")
        val path =
            "/app/index/rainfall/monitor/listRainfall?cityRegionCode=$cityRegionCode&districtRegionCode=$districtRegionCode&startTime=$startTime&endTime=$endTime"
        get(path)
    }

}