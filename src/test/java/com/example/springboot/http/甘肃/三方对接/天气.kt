package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 天气 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.DEV_THIRD
//        return ServerApi.PROD_THIRD
        return ServerApi.PRE_PROD_THIRD
    }

    @Test
    fun 天气(){
        val args = "area=兰州"
        val path = "/adapter/weather/area-to-weather?$args"

        get(path)
    }

    @Test
    fun 未来7天气(){
        val args = "area=兰州&date=20220227"
        val path = "/adapter/weather/area-to-weather-date?$args"

        get(path)
    }

    @Test
    fun 未来15天气(){
        val args = "areaCode=620100"
        val path = "/adapter/weather/day15?$args"

        get(path)
    }
}