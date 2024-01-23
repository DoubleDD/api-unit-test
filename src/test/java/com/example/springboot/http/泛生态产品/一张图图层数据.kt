package com.example.springboot.http.泛生态产品

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.net.URLEncoder

class 一张图图层数据 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_ONEMAP_MANAGER
    }

    override fun after() {
    }

    @Test
    fun 图层数据列表() {
        val baseInfoId = "1735584835091369984"
        val pageNum = 1
        val pageSize = 10
        val condition = "{}"
        val orderBy = ""
        val regionCode = "620100"

        val queryString =
            "regionCode=${regionCode}&baseInfoId=${baseInfoId}&pageNum=${pageNum}&pageSize=${pageSize}&condition=${
                URLEncoder.encode(condition)
            }&orderBy=${orderBy}"
        formatJson.set(true)
        get("/layerconfig/onemapLayerListConfig/data/page?${queryString}")
    }

    @Test
    fun 详情弹窗() {
        val baseInfoId = "1729809438198493184"
        val dataId = 620104000001
        val tabId = 1732413027916005377

        val queryString = "baseInfoId=${baseInfoId}&dataId=${dataId}&tabId=${tabId}"
        formatJson.set(true)
        get("/layerconfig/onemapLayerListConfig/data/detail?${queryString}")
    }

    @Test
    fun 撒点() {
        val regionCode = "620100"
        val baseInfoId = "1735584835091369984"
        get("/layerconfig/onemapLayerBaseInfo/gis/points?layerId=${baseInfoId}&regionCode=${regionCode}")
    }
}