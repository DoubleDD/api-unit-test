package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 考试星 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    @Test
    fun 考试星单点() {
        val path =
            "http://60.13.54.71:8999/api/company/data/1/?jwt=eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDg1MjQ4Mzk5NzAsImFjdGlvbl9pZCI6IiJ9.c5lPUuL__ttGoIlcXAc1F4e-CHtgWxvA4gT-QhKEH9U"
        val body = """
           {"role":"staff","user_id":"kedong1","user_name":"柯栋","department":"部门分类"}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }
}

class 大平台服务 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MzI5LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNWJhMGY3MjFjMzBkNDI0NmFjZWMwY2IwZWI4ZWQ4ZTciLCJpYXQiOjE2OTA0NDc3NDUsInVzZXJuYW1lIjoia2Vkb25nMSJ9.JNEgZVhiMn3WXdNEqYiilfkTG4HUcYnkt2GMlNyI7UU
        """.trimIndent()
    }

    @Test
    fun 分配考生角色() {
        val roleId = "e7e49a9cacd0478f93c4eb1074f2096c"
        val path = "/api/role/setRoletoAllUser"
        val body = "roleId=$roleId"
        post(path, body, MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 导出用给考试星() {
        get("/api/user/exportKsxData?pageNum=4")
    }
}