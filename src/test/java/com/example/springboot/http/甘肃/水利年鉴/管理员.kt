package com.example.springboot.http.甘肃.水利年鉴

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 管理员 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_YEARBOOK
//        return ServerApi.DEV_YEARBOOK
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MzAsImdyb3VwSWQiOiIwMTI0MTJkMmE3YzI0NjFlMTkzZjM2NzYzM2YxM2MxOCIsImFwcElkIjoiZ2Fuc3VfYXBwbHkiLCJ1c2VySWQiOiI5NGYzOTYyYTYzODk2NjAzN2ZmYTlmN2Q3YzI2Y2RjMyIsInV1SWQiOiJlMjhkOGE4NDM5ZmI0ZTc0OTVmNTkwY2RiOTdkZWQ4OSIsImlhdCI6MTY1Nzg2NTM2NSwidXNlcm5hbWUiOiJrZWRvbmcxIn0.rmCe4kOS0mLxGIXqNu56zXTikrnUnccoEIsczx4xh80
        """.trimIndent()
    }

    @Test
    fun 部门用户树() {
        val deptId = ""
        get("/admin/tree?deptId=$deptId")
    }

    @Test
    fun 用户搜索() {
        val key = "kedong"
        get("/admin/user/search?key=$key")
    }

    @Test
    fun 用户搜索树状结构() {
        val key = "胡"
        get("/admin/user/tree/search?key=$key")
    }

    @Test
    fun 查询已授权的管理员() {
        get("/admin/list")
    }

    @Test
    fun 授权() {
        @Language("JSON") val body = """
             [
               "94f3962a638966037ffa9f7d7c26cdc3"
             ]
        """.trimIndent()
        val path = "/admin/grant"
        post(path, body, MediaType.APPLICATION_JSON)
    }


    @Test
    fun 取消授权() {
        @Language("JSON") val body = """
            [
              "94f3962a638966037ffa9f7d7c26cdc3"
            ]
        """.trimIndent()
        val path = "/admin/grant/cancel"
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 管理员导出() {
        val path = "/manuscripts/export/taskAssignmentList"
        val body = """
            {"manuscriptsId":"1549706896743538690","missionPhase":0}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

}