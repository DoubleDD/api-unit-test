package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders


class 应用中台api服务测试 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.DEV_THIRD
//        return ServerApi.LOCAL_THIRD
    }

    override fun getToken(): String {
       return """
           eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MTQzLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNDBiMDEyZDFmYWM3NDE2MmE2NGE5ZDM4NzgwZWY4YTgiLCJpYXQiOjE2Njk5NzAwNzgsInVzZXJuYW1lIjoia2Vkb25nMSJ9.MWTHObDFaqfCXxG3N5z1s74vRrSA0nvwTPRJ7-7g_-4
       """.trimIndent()
    }

//    @RepeatedTest(1)
    @Test
    fun 通讯录树() {
        val path = "/adapter/mid-api/api/apihub/call/pub/dept_tree"
        val body = """
            {
                "headers": {
                    "token": "$token"
                },
                "params": {
                    "deptId": "816fbf3349234e16b9da11055e07bb7f"
                },
                "body": {}
            }
        """.trimIndent()
        post(path, body, commonHeaders())
    }

    @RepeatedTest(1)
    @Test
    fun 通讯录详情() {
        val path = "/adapter/mid-api/api/apihub/call/pub/addressList_detail"
        val body = """
            {
                "headers": {},
                "params": {
                    "id": "20211110114947907960228283420672"
                },
                "body": {}
            }
        """.trimIndent()
        post(path, body, commonHeaders())
    }



    @Test
    fun test(){
//        for (i in 1..10)
//            launch println("a")
    }
}

fun commonHeaders(): HttpHeaders {
    val headers = HttpHeaders()
    headers["Content-Type"] = "application/json;charset=UTF-8"
    headers["systemCode"] = "GSZHSLAPP"
    headers["appSecret"] = "bff80d66492f4977b0d42c91578ce693"
    return headers
}