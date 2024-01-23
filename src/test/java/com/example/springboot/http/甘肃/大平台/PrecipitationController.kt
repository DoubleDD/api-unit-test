package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class PrecipitationController : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.DEV_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MTgwLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiMTk2ZTNmNmQ0NDRmNDI2OWI1MDI3YzI3MmU5NTJjMDgiLCJpYXQiOjE2NTQ2ODM3MTIsInVzZXJuYW1lIjoia2Vkb25nMSJ9.5lGnm4_i0oYC2wvaBosmmsFKowUYOIj63lRQxdIoKRo
        """.trimIndent()
    }

    @Test
    fun getChartListOneMap() {
        val path = "/api/precipitation/getChartListOneMap?year=2018"
        get(path)
    }

}