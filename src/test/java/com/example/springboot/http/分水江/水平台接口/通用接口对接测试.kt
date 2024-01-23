package com.example.springboot.http.分水江.水平台接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import java.net.URLEncoder

/**
 * 通用接口对接测试
 */
class 通用接口对接测试 : BaseTest() {
    @AfterEach
    override fun after() {
        super.after()
        val body = (response.body)
        assert(body.startsWith("{") && body.endsWith("}"))
    }

    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_FSJ_8080
    }

    override fun getToken(): String {
//        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI1NDkwY2MzYTAwMzc0ODcwOGI2NTkwZTBjMThiZmUzNiIsInJhbmRvbSI6OTUyLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiN2EyZjIzZGMyM2U4NDA5OWY2ZTM0M2FmNjliOTI4NWQiLCJ1dUlkIjoiMjMyNDZkZWNhMzZiNGI2ZWI1NzExM2U3MmY3ZjM2NjEiLCJpYXQiOjE2MzcyODY3MTEsInVzZXJuYW1lIjoieWFuZ3llemhvdSJ9.ptbK0C8ew2Y4qdq6n0geoVPbQTN8D4U6hPpQ0BiauCk"
        return "3812d60f-61c0-43c1-9172-93255c957e66-707286"
    }

    /**
     * 获取厂商配置
     */
    @Test
    fun 获取厂商配置() {
        val name = "fsj_visual"
        formatJson.set(true)
        request("/api/config/${name}", HttpMethod.GET, null)
    }


    @Test
    fun 接口调用同步数据() {
        var name = "小型水库基础信息"
        name = "大中型水库基础信息"
        name = "堤防基础信息"
        name = "闸站基础信息"
        name = "电站基础信息"
        name = "病险水库信息"
        name = "堤防标绘信息"
        name = "河道防洪指标"
        name = "河段基础信息"
        name = "取用水户基础信息"
        name = "流域基础信息"
        name = "取水口"
        name = "病险水库信息_业务库"
        name = "电站基础信息_业务库"
        name = "大中型水库测站关系_业务库"
        name = "小型水库测站关系_业务库"
        name = "安全责任人信息_业务库"
        name = "山塘基础信息_业务库"
        name = "农村水电站生态流量信息_业务库"
        name = "农村水电站实时流量监测点关联信息_业务库"
        name = "水厂管理对象名录_业务库"

        name = "地表水水源地基本信息_业务库"
        request("/test/httpDataToDb?name=${URLEncoder.encode(name, "UTF-8")}", HttpMethod.GET, null)
    }


    @Test
    fun tokenTest() {
        val module = "shuiziyuan"
        get("/adapter/token/${module}")
    }



}