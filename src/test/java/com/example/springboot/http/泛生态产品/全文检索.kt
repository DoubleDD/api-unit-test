package com.example.springboot.http.泛生态产品

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 全文检索 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_ONEMAP_SEARCH
    }

    override fun after() {
    }

    @Test
    fun 周边查询() {
        val suggestKeyword = "兰州市"
        val circleLat = 36.057731
        val circleLon = 103.825983
        val distanceKm = 25
        val cdataTypes = 1737302302791368704

        formatJson.set(true)
        val queryString =
            "suggestKeyword=${suggestKeyword}&circleLat=${circleLat}&circleLon=${circleLon}&distanceKm=${distanceKm}&cdataTypes=${cdataTypes}"
        get("/onemapLayer/data/listCdataTypeItemByGisCircle?${queryString}")
    }


    @Test
    fun 行政区划搜索() {
        formatJson.set(true)
        val regionCode = "620100"
        get("/onemapLayer/data/queryTypeStatisByArea?regionCodes=${regionCode}")
    }
}