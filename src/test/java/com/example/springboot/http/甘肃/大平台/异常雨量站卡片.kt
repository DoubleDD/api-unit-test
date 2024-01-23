package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 异常雨量站卡片 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.PRE_PROD_PORTAL
    }


    @Test
    fun 获取异常雨量站列表() {
        val path = "/api/rainfall/station/abnormal"
        val st = "2022-12-28 08:00:00"
        val et = "2022-12-19 08:00:00"
        val v = 0
        post(path, "st=$st&et=$et&v=$v", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 启用异常雨量站() {
        val path = "/api/rainfall/station/disable"
        val enable = 0
        val stationCode = "01413090"
        post(path, "enable=$enable&stationCode=$stationCode", MediaType.APPLICATION_FORM_URLENCODED)
    }


    @Test
    fun 设置阈值() {
        val path = "/api/rainfall/station/abnormal/value"
        val value = 123
        post(path, "value=$value", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 获取异常阈值() {
        val path = "/api/rainfall/station/abnormal/value"
        get(path)
    }
}