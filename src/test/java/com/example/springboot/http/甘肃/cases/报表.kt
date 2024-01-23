package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 报表:BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_THIRD
        return ServerApi.PRE_PROD_THIRD
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MjYyLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiYzQ1NThhMjI5ZGUyNGNjZThhMTJkZjMzY2Q1M2FmZTUiLCJpYXQiOjE2NDk3MzQ5NDcsInVzZXJuYW1lIjoia2Vkb25nMSJ9.ShXmTEKj77IEaPBA1pw326PouUmbpBvi4NKU2woLmAI
        """.trimIndent()
    }

    @Test
    fun loginUrl(){
        val path = "/report/adapter/?token=$token"
        get(path)
    }

    @Test
    fun 数据中台单点(){
        "/api/data/center/syncPwd"
        val path = "/api/data/center/syncPwd?userId=1c7d661fa79d96ab1efb79cdee7b85b4&password=gssl@123456"
        get(path)
    }

}