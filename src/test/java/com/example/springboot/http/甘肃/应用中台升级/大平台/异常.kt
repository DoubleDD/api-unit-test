package com.example.springboot.http.甘肃.应用中台升级.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 异常:BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.DEV_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6OTM3LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiYmVjNTY5NGZmOWIzNDU0NzhmYTJlNjM5YTdlMDdhZjkiLCJpYXQiOjE2NTQ3NDU0ODUsInVzZXJuYW1lIjoia2Vkb25nMSJ9.eui9PlzCILVMs7qScqjjJxlAkwm5GpFxgZug6DxzgAo
        """.trimIndent()
    }

    @Test
    fun 详情(){
        val id = "4f34d3572e6e480da600da95f5c7adc9"
        get("/exception/detail/$id")
    }
}