package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod


class 水保接口测试 : BaseTest() {

    override fun getServer(): ServerApi {
//        return ServerApi.DEV_THIRD
        return ServerApi.LOCAL_THIRD
//        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.PROD_THIRD
    }

    override fun getToken(): String {
        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI1NDkwY2MzYTAwMzc0ODcwOGI2NTkwZTBjMThiZmUzNiIsInJhbmRvbSI6NDk4LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiN2EyZjIzZGMyM2U4NDA5OWY2ZTM0M2FmNjliOTI4NWQiLCJ1dUlkIjoiZjE0NDI3MDZjNzBmNGFmMzkxNWJlZjBkYzRkOWNhZjQiLCJpYXQiOjE2MzcxMTE5NjIsInVzZXJuYW1lIjoieWFuZ3llemhvdSJ9.Zi21mFF6wJkordZAme26XXGoIGw30QtB94AZflhyU90"
        return token
    }


    @AfterEach
    override fun after() {
        super.after()
        val body = (response.body)
        assert(body.startsWith("{") && body.endsWith("}"))
    }

    @Test
    fun 方案列表() {
        val url =
            "/adapter/xmstbcjg/shuibao-supervision/supProj/getWaterUnitProjInfoList?levelId=&pageSize=10&pageNum=2"
        request(url, HttpMethod.GET, null)
    }

    @Test
    fun 於地坝水位列表() {
        val url =
            "/adapter/xmstbcjg/shuibao-governance/siltDam/swc-gov-warter-info/getSwcGovWarter?page=1&rows=10&addvcd=620000000&keyword=&time=&_t=1636101231594"
        request(url, HttpMethod.GET, null)
    }

    @Test
    fun 於地坝水位详情() {
        val url =
            "/adapter/xmstbcjg/shuibao-governance/siltDam/getSiltDamInfoById?sltdam_id=491f12c19db64fc58d294dd8c5fadfd9&_t=1636101453585"
        request(url, HttpMethod.GET, null)
    }

    @Test
    fun 於地坝水位导出详情() {
        val url =
            "/card/export/getSwcGovWarter/detail?page=1&rows=10&addvcd=620000000&keyword=&time=30min&_t=1636101738073"
        request(url, HttpMethod.GET, null)
    }

    @Test
    fun 於地坝雨量列表() {
        val url =
            "/adapter/xmstbcjg/shuibao-governance/siltDam/swc-gov-rain-info/getSwcGovRainInfo?page=1&rows=10&addvcd=620000000&keyword=&time=30min&_t=1636101738073"
        val header = HttpHeaders()
        header["token"] =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI1NDkwY2MzYTAwMzc0ODcwOGI2NTkwZTBjMThiZmUzNiIsInJhbmRvbSI6NTUsImdyb3VwSWQiOiIwMTI0MTJkMmE3YzI0NjFlMTkzZjM2NzYzM2YxM2MxOCIsImFwcElkIjoiZ2Fuc3VfYXBwbHkiLCJ1c2VySWQiOiI3YTJmMjNkYzIzZTg0MDk5ZjZlMzQzYWY2OWI5Mjg1ZCIsInV1SWQiOiJhZjJhODM1NTY5OTQ0ZWJhYjg4ZjYwYzAzODc0NDc0ZiIsImlhdCI6MTYzNjczOTg2MCwidXNlcm5hbWUiOiJ5YW5neWV6aG91In0.J82TCRXQi7aFVlzqsgvzkDJk5ViR9tPZY4lFnYq08lg"
        header["YL-Token"] =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI1NDkwY2MzYTAwMzc0ODcwOGI2NTkwZTBjMThiZmUzNiIsInJhbmRvbSI6NTUsImdyb3VwSWQiOiIwMTI0MTJkMmE3YzI0NjFlMTkzZjM2NzYzM2YxM2MxOCIsImFwcElkIjoiZ2Fuc3VfYXBwbHkiLCJ1c2VySWQiOiI3YTJmMjNkYzIzZTg0MDk5ZjZlMzQzYWY2OWI5Mjg1ZCIsInV1SWQiOiJhZjJhODM1NTY5OTQ0ZWJhYjg4ZjYwYzAzODc0NDc0ZiIsImlhdCI6MTYzNjczOTg2MCwidXNlcm5hbWUiOiJ5YW5neWV6aG91In0.J82TCRXQi7aFVlzqsgvzkDJk5ViR9tPZY4lFnYq08lg"
//        request(url, HttpMethod.GET, HttpEntity(header))
        get(url)
    }

    @Test
    fun 於地坝雨量详情() {
        val url =
            "/adapter/xmstbcjg/shuibao-governance/siltDam/getSiltDamInfoById?sltdam_id=491f12c19db64fc58d294dd8c5fadfd9&_t=1636101816588"
        request(url, HttpMethod.GET, null)
    }

    @Test
    fun 水土保持方案列表() {
        val args = """
            pageSize=10&pageNum=1&levelId=2&addvcd=620000&search_time=2021-01-01,2022-04-22
        """.trimIndent()
        val url =
            "/adapter/xmstbcjg/shuibao-supervision/supProj/getSwcSupProjInfoList?$args"
        request(url, HttpMethod.GET, null)
    }

    @Test
    fun 於地坝雨量导出详情() {
        val url =
            "/card/export/getSwcGovRainInfo/detail?page=1&rows=10&addvcd=620000000&keyword=&time=30min&_t=1636101738073"
        val header = HttpHeaders()
        header["token"] =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI1NDkwY2MzYTAwMzc0ODcwOGI2NTkwZTBjMThiZmUzNiIsInJhbmRvbSI6NTUsImdyb3VwSWQiOiIwMTI0MTJkMmE3YzI0NjFlMTkzZjM2NzYzM2YxM2MxOCIsImFwcElkIjoiZ2Fuc3VfYXBwbHkiLCJ1c2VySWQiOiI3YTJmMjNkYzIzZTg0MDk5ZjZlMzQzYWY2OWI5Mjg1ZCIsInV1SWQiOiJhZjJhODM1NTY5OTQ0ZWJhYjg4ZjYwYzAzODc0NDc0ZiIsImlhdCI6MTYzNjczOTg2MCwidXNlcm5hbWUiOiJ5YW5neWV6aG91In0.J82TCRXQi7aFVlzqsgvzkDJk5ViR9tPZY4lFnYq08lg"
        request(url, HttpMethod.GET, HttpEntity(header))
    }
}