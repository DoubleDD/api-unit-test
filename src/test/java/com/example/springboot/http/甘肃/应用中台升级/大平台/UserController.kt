package com.example.springboot.http.甘肃.应用中台升级.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class UserController : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_PORTAL
//        return ServerApi.PRE_PROD_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6Nzc2LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiMDEzNWI2NjQyZTRiNGM0MWI4YjlhMjRlYWI0YzFjY2UiLCJpYXQiOjE2ODYzMTgyNjgsInVzZXJuYW1lIjoia2Vkb25nMSJ9.MUxSSlJhG_NEQUXCoKrHapzgkRFEjX_u3F3b-h7tqxk
        """.trimIndent()
    }

    @Test
    fun 新增用户() {
        val path = "/api/user/add"

        @Language("JSON")
        val body = """
            {
              "deptId": "b8a404dd89bc4e1eba07250f3c1acea1",
              "deptName": "兰州市水务局",
              "userName": "test3",
              "realname": "test3",
              "gender": 1,
              "tel": "13277988809",
              "roleIds": [
                ""
              ],
              "normalAdminFlag": "0",
              "roleBind": [
                {
                  "roleId": "cde3255d02ff49f7abbd6852890a6f65",
                  "flag": 0
                },
                {
                  "roleId": "4de51f7508fb4efeaac48df7cc19344b",
                  "flag": 0
                }
              ]
            }
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 用户列表() {
        formatJson.set(true)
        val path = "/api/user/v2/pageList"
        val body = """
        {"pageNum":1,"pageSize":10,"realname":"大雄","ustatus":"","scheduleType":"1","deptId":"0"}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 启用用户() {
        val userId = ""
        get("/api/user/enable?id=$userId")
    }

    @Test
    fun 禁用用户() {
        val userId = ""
        get("/api/user/disable?id=$userId")
    }

    @Test
    fun 删除用户() {
        val userId = ""
        get("/api/user/delete?id=$userId")
    }


    @Test
    fun 重置用户密码() {
        val userId = ""
        get("/api/user/reset?id=$userId")
    }

    @Test
    fun 修改密码接口() {
        val path = "/api/user/modifyPassword"
        val body = """
          {"newPasswd":"eaca27c9076e0b602ffa577ffddc59fc1691c9e2","oldPasswd":"4606536168acfbd92da3d93e5679ad9dbc257de3"} 
       """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 用户绑定角色() {
        val path = "/api/user/addUserRoles"
        val body = """
           
       """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 通过token查用户信息() {
        val path = "/api/user/userInfo?token=$token"
        get(path)
    }


}