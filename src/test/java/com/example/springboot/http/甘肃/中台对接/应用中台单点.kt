package com.example.springboot.http.甘肃.中台对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 应用中台单点 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJmODExY2M4NjNlOWY0N2U2Yjk2MjIxOWRiMjhjY2JhYyIsInJhbmRvbSI6NDYxLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiNDY3NzNhYWFhN2E0ZmUwMGJiZjkxNWRjMzFkOGQzYWQiLCJ1dUlkIjoiN2RiNmE3NTAwYThlNDAxZWJlMGRiYjJlY2QzNDMzNzQiLCJ1c2VybmFtZSI6ImFkbWluMSJ9._txgpGOHHAhNTONuMuGRA-Wc3Zr3_Z5ZpSPqshCtkwM
        """.trimIndent()
    }

    @Test
    fun 应用中台() {
        get("/api/mid/application/index?token=$token")
    }
}