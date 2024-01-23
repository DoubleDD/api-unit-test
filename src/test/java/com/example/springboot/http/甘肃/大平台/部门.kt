package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 部门 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.PRE_PROD_PORTAL
        return ServerApi.LOCAL_PORTAL
    }


    @Test
    fun 获取部门全路径() {
        val ids = """
            [
                "16603dfc1b1b8e3f310e52b966f8db6f",
                "4dda6f5e8774df6e81faf8da7c8949e1",
                "7516344222184945ae650e9f2afe7e67",
                "7eb173b1e1673b48fc12c05f771395ad",
                "858371bc9dfcd086481fe7fc6e8cbd41"
            ]
        """.trimIndent()
        val path = "/api/department/getDeptTreeNames"

        post(path, ids, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 部门更新测试() {
        val path = "/api/department/update"
        val body = """
            {"hypostaticStatus":1,"deptName":"庆阳市盐环定扬黄续建工程建设管理局","id":"6435e0fb7373c1f19357bfa063742eb7","pid":"b6c1d5c1eb454b8fb2c0500d2903905f","regionId":1414558}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 获取用户行政区划() {
        val userId = "94f3962a638966037ffa9f7d7c26cdc3"
        val dataAuth = 1
        get("/api/department/queryRegionTreeByUserId?userId=$userId&dataAuth=$dataAuth")
    }


    @Test
    fun 批量创建部门() {
        val path = "/api/department/importData"
        postFile(path, "/home/kedong/tmp/未命名 2.xls")
    }


    @Test
    fun 用户部门关系修复() {
        val path = "/api/department/repairUserDep"
        postFile(path, "/home/kedong/Downloads/未命名 1.xls")
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NTExLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiZjM4YzhlYTA4ODliNDc0MmI0NTBlYjBmNDg1N2NiMzQiLCJpYXQiOjE2ODEyODQ4MTAsInVzZXJuYW1lIjoia2Vkb25nMSJ9.HDq3Lbp_HMPTjFrK9XSD09D7s3K6eF-ZtOxVkg_YOpQ
        """.trimIndent()
    }

    @Test
    fun 查询登录用户所在的部门管理员() {
        val userId = "3c43e46cbbbf4dd7a65d6091fe02634e"
        val path = "/api/department/administrator?userid=$userId"
        get(path)
    }
}