package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 角色相关接口 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.PROD_PORTAL
//        return ServerApi.PRE_PROD_PORTAL
//        return ServerApi.DEV_PORTAL
    }



    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6ODQxLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiODMwZDA4YjZiNTQwNGJjMjkyZTI4YjEwMGNjNjgzNGQiLCJpYXQiOjE2OTI4NjI2MTgsInVzZXJuYW1lIjoia2Vkb25nMSJ9.w_ZqyzoGPKfH1CL1o-U9en5qbGIYNDrX4Q9IXX0LnyM
        """.trimIndent()
    }

    @Test
    fun 批量绑定角色用户关系() {
        val path = "/api/role/bind/user/import"
        val fileName = "/Users/kedong/code/com.gitee/yunli/甘肃/水利工程角色绑定.xlsx"
        postFile(path, fileName)
    }



    @Test
    fun 给所有用户绑定指定角色() {
        val roleId = "1eedd28c625b4f5b8a0b0d7b97160fca"
        val path = "/api/role/setRoletoAllUser"
        post(path, "roleId=$roleId", MediaType.APPLICATION_FORM_URLENCODED)
//        postFile(path, "/home/kedong/Documents/Book1.xlsx")
    }

    @Test
    fun 角色绑定用户() {
        val path = "/api/role/setRoleUser"
        val body = """
            {"roleId":"bee6cdaae3e74035a01ea18e882a5211","userIdList":["5cd783b44020a92484908c6efd94eb8a"]}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }


}