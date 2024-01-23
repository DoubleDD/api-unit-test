package com.example.springboot.http.甘肃.数据中台厂商对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.net.URLEncoder


class GNSS : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }


    @Test
    fun getGnssData() {
        val code = "gnss1"
        val limit = "1000000"
        val page = "1"
        val startDate = URLEncoder.encode("2022-09-07 00:00:00")
        val endDate =URLEncoder.encode("2022-09-08 00:00:00")
        val path = "http://125.75.233.239:15001/getGnssData?code=$code&limit=$limit&page=$page&startDate=$startDate&endDate=$endDate"
        get(path)
    }


}