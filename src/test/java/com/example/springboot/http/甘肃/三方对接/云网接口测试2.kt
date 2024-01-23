package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

/**
 * 云网安全接口单元测试
 */
class 云网接口测试2 : BaseTest() {
    @AfterEach
    override fun after() {
        super.after()
        val body = (response.body)
        assert(body.startsWith("{") && body.endsWith("}"))
    }

    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_THIRD
        return ServerApi.PRE_PROD_THIRD
    }

    override fun getToken(): String {
//        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI1NDkwY2MzYTAwMzc0ODcwOGI2NTkwZTBjMThiZmUzNiIsInJhbmRvbSI6OTUyLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiN2EyZjIzZGMyM2U4NDA5OWY2ZTM0M2FmNjliOTI4NWQiLCJ1dUlkIjoiMjMyNDZkZWNhMzZiNGI2ZWI1NzExM2U3MmY3ZjM2NjEiLCJpYXQiOjE2MzcyODY3MTEsInVzZXJuYW1lIjoieWFuZ3llemhvdSJ9.ptbK0C8ew2Y4qdq6n0geoVPbQTN8D4U6hPpQ0BiauCk"
        return "3812d60f-61c0-43c1-9172-93255c957e66-707286"
    }

    /**
     * 获取告警规则
     */
    @Test
    fun 获取告警规则() {
        val url = "/adapter/unicom-cloud-0/backend/monitor/alarm-strategy/get-rule"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 获取告警数据
     * backend/monitor/alarm-log/list?page=1&limit=1&time_type=1&start_time=1636441146066&end_time=1636527546066
     */
    @Test
    fun 获取告警数据() {
        val url = "/adapter/unicom-cloud-0/backend/monitor/alarm-strategy/list?page=1&limit=10"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 顶部数据
     */
    @Test
    fun 顶部数据() {
        val url = "/adapter/unicom-cloud-0/backend/analysis/tenant/index?az_id=Lanzhou"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 24小时告警事件统计卡片
     */
    @Test
    fun 二十四小时告警事件统计卡片() {
        val url =
            "/adapter/unicom-cloud-0/backend/monitor/alarm-log/list?page=1&limit=10&time_type=1&start_time=1635411534339&end_time=1635497934339"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 安全组件类型
     */
    @Test
    fun 安全组件类型() {
        val url = "/adapter/unicom-cloud-0/backend/safekit/elegance/machines?page=1&limit=20&expire=0"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 近一小时的高危攻击
     */
    @Test
    fun 近一小时的高危攻击() {
        val url =
            "/adapter/unicom-cloud-0/backend/monitor/alarm-log/list?page=1&limit=10&time_type=7&start_time=1635501600000&end_time=1635505200000"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 近一小时的高危攻击 弹窗详情
     */
    @Test
    fun 弹窗详情() {
        val url = "/adapter/unicom-cloud-0/backend/monitor/alarm-log/info?id=70335"
        request(url, HttpMethod.GET, null)
    }


    @Test
    fun oneAgg_cpu() {
        val path = "/network/cmsc-statistics"
        val endTime = System.currentTimeMillis()
        val startTime = System.currentTimeMillis() - (7 * 3600 * 1000)
        val body =
            "{\"collection\":\"cpu\",\"equalCondition\":{\"timeType\":1440},\"rangeCondition\":{\"beginTime\":[$startTime,$endTime]},\"returnFields\":{\"averUsage\":0}}"
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun oneAgg_mem() {
        val path = "/network/cmsc-statistics"
        val endTime = System.currentTimeMillis()
        val startTime = System.currentTimeMillis() - (7 * 24 * 3600 * 1000)
        val body =
            "{\"collection\":\"mem\",\"equalCondition\":{\"timeType\":1440},\"rangeCondition\":{\"beginTime\":[$startTime,$endTime]},\"returnFields\":{\"averUsedPercent\":10}}"
        post(path, body, MediaType.APPLICATION_JSON)
    }
}