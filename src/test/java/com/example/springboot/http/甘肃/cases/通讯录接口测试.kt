package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 通讯录接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.DEV_PORTAL
//        return ServerApi.LOCAL_PORTAL
//        return ServerApi.PRE_PROD_PORTAL
        return ServerApi.PROD_PORTAL
    }


    @Test
    fun 详情() {
//        val id = "006b2c3c2c1140e2903c256cc7c85fe7"
//        val id = "d32b575af09a4701bc8e2647c07179cd"
        val id = "20211110114947907960228283420672"
        val path = "/api/addressList/detail?id=$id"
        get(path)
    }

    @Test
    fun 按单位查询接口() {
        val deptId = "e6c36ca12587491b9bb01c315c9ec097"
        val path = "/api/addressList/v2/dept/tree?regionId=&deptId=$deptId"
        val header = HashMap<String,String>()
        header["User-Agent"] = "dart:io"
        formatJson.set(true)
        get(path,header)
    }


    @Test
    fun 绑定用户id() {
        val name = "仲伟斌"
        val addressId = ""
        val userId = ""
        val path = "/api/addressList/binduserid?name=$name&addressId=$addressId&userId=$userId"
        get(path)
    }

    override fun getToken(): String {
        return """
        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI1NDkwY2MzYTAwMzc0ODcwOGI2NTkwZTBjMThiZmUzNiIsInJhbmRvbSI6OTg2LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiN2EyZjIzZGMyM2U4NDA5OWY2ZTM0M2FmNjliOTI4NWQiLCJ1dUlkIjoiMDIzYmQyYzYzM2M0NGRlZTgxNzM4ODYxOWVlODQ1N2IiLCJpYXQiOjE2OTIwNjg4ODMsInVzZXJuYW1lIjoieWFuZ3llemhvdSJ9.fVHZkncSEAMHvM0ILoQGWL-Nc4oRspRoxE5or0a_Ng0
        """.trimIndent()
    }

    @Test
    fun 导入测试() {
        val path = "/api/addressList/importData"
        postFile(path, "/home/kedong/Documents/工作/云粒智慧/钉钉/通讯录导入模板 (5)(1).xlsx")
    }
}