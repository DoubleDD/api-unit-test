package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class App首页 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.PRE_PROD_PORTAL
//        return ServerApi.PROD_PORTAL
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.DEV_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJiYjJkYWJlNjEzNWQ0ODY5YjEwYTg0N2JhNjhiOGIwOSIsInJhbmRvbSI6Njg5LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiNzRmN2YxOTA3OGQ0M2UyOWFiNDEyMWM1Y2VlMjU2YTciLCJ1dUlkIjoiNWQ2ODg0MDJkZDdkNDdkMzkzNmU0ODI5ZjRmNzIxYTMiLCJpYXQiOjE2Njg1MDAxNjEsInVzZXJuYW1lIjoidGVzdDAwMSJ9.RNsykF7-3GFXhTP7WYWj9jhCEi9OzEjU6WmxZmJUKlA
        """.trimIndent()
    }

    @Test
    fun App首页待办事项数量() {
        val path = "/api/appRemote/statTodo"
        post(path, "", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun web首页待办数量() {
        val path = "/OA/flow/listAllOAFlowCount"
        val userId = "lixiaoning"
        post(path, "userId=$userId", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 事件基础信息() {
        val regionCode = "620000"
        val startDate = "2022-01-01"
        val endDate = "2022-12-31"
        val path = "/api/riverLaker/queryEventInfomation2?regionCode=$regionCode&startDate=$startDate&endDate=$endDate"
        get(path)
    }
}

class App : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_APP
//        return ServerApi.PRE_PROD_APP
//        return ServerApi.DEV_APP
//        return ServerApi.LOCAL_APP
    }

    override fun getToken(): String {
        """
        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzMGQzMzI1MWVhNmU0MThmOWE1Y2RlN2EyMTIzZTc1ZCIsInJhbmRvbSI6MTc3LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiZTUzNDFlOTY3MDNkYmFmYjIxNWZkOGRlMWQ4NTk2YTIiLCJ1dUlkIjoiODJiNmM2MDMxOGYwNDNkYzk0NzhmYjI5YzZkNWY1ZGQiLCJpYXQiOjE2ODg5ODI3NTIsInVzZXJuYW1lIjoicXVsZWkifQ.a_dkwUa-UAZFvhykCalrQfHiO2vjBn57JDe_vIP9Kx8
        """.trimIndent()
        return """
        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJmNGE2NDI2NDZmMzM0ZGM0YTNmZjM1NjI2ZDI2YmFlNiIsInJhbmRvbSI6MTg1LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiMmI1YmZmODcyNGY3MmY5NzY3ZmI3OTdmMjU5OGQxMmUiLCJ1dUlkIjoiOWU1ZDI0NTFkMjQxNGI0MDg2MDdjYWMzNmYyZGExZmUiLCJpYXQiOjE2ODg5ODI5OTUsInVzZXJuYW1lIjoid2VpbG9uZyJ9.JYL9FFj747n-pvrSNaR6kXn819hutGjOg7F9dAEdacQ
        """.trimIndent()
    }

    @Test
    fun 首页轮播() {
        val regionCode = "620000000000"
        val path = "/api/home/homeStat?regionCode=$regionCode"
        get(path)

    }

    @Test
    fun 首页轮播待办v2() {
        get("/api/home/homeStat/todo/v2")
    }

    @Test
    fun 首页轮播待办() {
        val regionCode = "620000000000"
        val path = "/api/home/homeStat/todo?regionCode=$regionCode"
        get(path)

    }

    @Test
    fun 首页轮播雨水情() {
        val regionCode = "620000000000"
        val path = "/api/home/homeStat/rainfall?regionCode=$regionCode"
        get(path)

    }

    @Test
    fun 首页轮播河长制() {
        val regionCode = "620000000000"
        val path = "/api/home/homeStat/riverlake?regionCode=$regionCode"
        get(path)

    }

}

class 一张图 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_ONE_MAP
    }

    @Test
    fun 首页轮播河长制() {
        val regionCode = "620000000000"
        val path = "/api/rainfall/monitor/listAllByStationsCount?regionCode=$regionCode"
        get(path)

    }
}