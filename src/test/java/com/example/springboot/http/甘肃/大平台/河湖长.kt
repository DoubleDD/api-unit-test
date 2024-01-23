package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 河湖长 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.DEV_PORTAL
//        return ServerApi.LOCAL_PORTAL
    }


    @Test
    fun 河湖长数据饼图() {
        val regionCode = "621100"
        val type = "1"
        val path = "/api/riverLaker/queryBasicInformationChart?regionCode=$regionCode&type=$type"
        get(path)
    }
}