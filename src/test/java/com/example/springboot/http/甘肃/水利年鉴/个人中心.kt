package com.example.springboot.http.甘肃.水利年鉴

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 个人中心 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_YEARBOOK
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NjQsImdyb3VwSWQiOiIwMTI0MTJkMmE3YzI0NjFlMTkzZjM2NzYzM2YxM2MxOCIsImFwcElkIjoiZ2Fuc3VfYXBwbHkiLCJ1c2VySWQiOiI5NGYzOTYyYTYzODk2NjAzN2ZmYTlmN2Q3YzI2Y2RjMyIsInV1SWQiOiJkYzNkNzYzMzdhNzE0M2YxYTg1NDExNTM4YjRlZTZlZiIsImlhdCI6MTY3MzMzMjM0NCwidXNlcm5hbWUiOiJrZWRvbmcxIn0.Ska2uepUJm7rjA8tyKlJBvKdlahLpCji3NBTB5ZT8yw
        """.trimIndent()
    }

    @Test
    fun 批量重置特约编辑() {
        val path = "/manuscripts/batch/reset"
        val deptIds = arrayOf("816fbf3349234e16b9da11055e07bb7f", "31e27fc160f54bee8e9d6a3401729164")

        post(path, JSON.toJSONString(deptIds), MediaType.APPLICATION_JSON)
    }

    @Test
    fun 个人中心列表查询() {
        val args = "pageNum=1&pageSize=10&name=&deptId=816fbf3349234e16b9da11055e07bb7f&status=1"
        val path = "/manuscripts/competenceList?$args"

        get(path)
    }

    @Test
    fun 特约编辑身份移交() {
        val path = "/manuscripts/saveCompetence"
        val body = HashMap<String, String>()
        body["deptId"] = "942ce66587f9400f9c3d24a0c9db2198"
        body["editUser"] = "e0cfa6b4a9a040744a19ca480f3f81cf"
        body["editUserName"] = "yansanshao"

        post(path, JSON.toJSONString(body), MediaType.APPLICATION_JSON)

    }

    @Test
    fun 身份查询() {
        val deptId = "38fc2ef53acb4596b40c0d7c6f7b371c"
        val userid = "94f3962a638966037ffa9f7d7c26cdc3"
        val path = "/manuscripts/getIdentity?deptId=$deptId&userId=$userid"
        get(path)
    }


}