package com.example.springboot.http.甘肃.智慧河湖

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

class TestController : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_SR
    }

    val token1 = """
    eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIyODYxNThkY2Q0Y2I0NTA3YTYyN2M1OGE3ZTE4MzAzMiIsInJhbmRvbSI6OTQwLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiZWI2OWI4OGIzOGRkYzkwYjI1ODNhNjRiOGZlNmZjZjciLCJ1dUlkIjoiYThkMzI0M2NmMzc3NDY1YTkxNzM3Y2EyMzNiNzVmMGIiLCJpYXQiOjE2NTUyMDEwNzQsInVzZXJuYW1lIjoicXN4ayJ9.X5QGWp2iKTDxAv6SfQu0WEJiaDXg4F6_KQ3OAvJcbXk
""".trimIndent()

    @Test
    fun test() {
        val path = "/test/userInfo"
        val header = HttpHeaders()
        header.add(HttpHeaders.COOKIE, "token=$token1")
        get(path, header)
    }
}