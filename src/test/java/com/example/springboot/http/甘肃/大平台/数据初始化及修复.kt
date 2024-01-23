package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 数据初始化及修复 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.PROD_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MjM0LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiMWUyMTQ2ZDJjOWE4NDE3MTgzNDc1MmJiYzczZDM3NDEiLCJpYXQiOjE2NjY4NzYwMzEsInVzZXJuYW1lIjoia2Vkb25nMSJ9.lX6g4mY8Ew3x2IKUd4yGnU931YXzR0ig5mp9Kk9lf9E
        """.trimIndent()
    }

    @Test
    fun 管理员默认角色初始化(){
        val path = "/data/repair/admin/default/role"
        get(path)
    }

}