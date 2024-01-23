package com.example.springboot.http.安全问题.惠州河湖长

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

class 惠州河湖长越权测试 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_HUIZHOU_USER
//        return ServerApi.PROD_HUIZHOU_USER
    }

    private fun commonHeader(): HttpHeaders {
        val headers = HttpHeaders()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = "Qgi+4b19/+pTUjr+G3aaMpDY6n8eXbdylyo5maU4+fL4E+OQV+04vk0+txuo2nbEvI538OHlJG0="
        return headers
    }

    @Test
    fun 查询用户列表() {
        val path = "/api/user/list"
        val body = """
            {"regionCode":"441300000000","userName":"","realname":"","roleCode":"","includeSub":1,"showDisabled":0,"pageNum":1,"pageSize":15,"status":"","deptId":""}
        """.trimIndent()
        post(path, body, commonHeader())
    }


    @Test
    fun 新增用户() {
        val path = "/api/user/add"
        val body = """
           {"regionCode":"441300000000","userName":"test001","realname":"test001","roleCode":"1","permissions":["20190529153932583318518691401728"],"roleTagName":"河长","code":"441300000000","regionName":"惠州市","departments":[],"list":[]} 
        """.trimIndent()
        post(path, body, commonHeader())
    }

    @Test
    fun 删除用户() {
        val userId = "02210281441301035563969680642048"
        val path = "/api/user/delete?id=$userId"
        get(path, commonHeader())

    }

    @Test
    fun 导入用户列表模板() {
        val path = "/api/user/importUsers"
        val headers = HttpHeaders()
        headers["Authorization"] = "Qgi+4b19/+pTUjr+G3aaMpDY6n8eXbdylyo5maU4+fL4E+OQV+04vk0+txuo2nbEvI538OHlJG0="
        postFile(path, headers, "/home/haha/Downloads/user.ef7bc033.xlsx")
    }

    @Test
    fun 禁用用户() {
        val userId = "02210281441301035563969680642048"
        val path = "/api/user/disable?id=$userId"
        get(path, commonHeader())
    }

    @Test
    fun 重置用户密码() {
        val userId = "02210281441301035563969680642048"
        val path = "/api/user/reset?id=$userId"
        get(path, commonHeader())
    }

    @Test
    fun 重置用户密码1() {
        val userId = "02210281441301035563969680642048"
        val path = "http://113.96.111.117:7000/yunli/p/v1/api/file/upload"
        get(path, commonHeader())
    }
}

class huizhou_pub : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.DEV_HUIZHOU_PUB
//        return ServerApi.LOCAL_HUIZHOU_PUB
    }

    @Test
    fun download() {
        val path = "/api/appVersioning/queryPageList"
        val headers = HttpHeaders()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = ""
        headers["Authorization1"] = ""
        headers["Authorization2"] = ""
        headers["Authorization3"] = ""

        post(path, "",headers)
    }
}