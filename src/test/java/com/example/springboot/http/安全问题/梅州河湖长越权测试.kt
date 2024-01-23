package com.example.springboot.http.安全问题.惠州河湖长

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

class 梅州河湖长越权测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_HUIZHOU_USER
        return ServerApi.DEV_MEIZHOU_USER
    }

    @AfterEach
    override fun after() {
        assert(response.statusCode.value() == 403)
    }

    private fun commonHeader(): HttpHeaders {
        val headers = HttpHeaders()
        headers["Content-Type"] = "application/json"
        // 低权限 用户
        headers["Authorization"] =
            """
                eyJraWQiOiIyMDIyMTEwMTE3MDEwOTEwMzcwNDg2Njc5NDY3NDk5NTIiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsInNpZCI6IjU4OGNhNWUyYTg1MDRiNjM4N2ZjNGFjNjRhMWRlNDdhIn0.eyJyb2xlSWRzIjpbIjIwMjEwOTI0MTAxNzIyODkwOTA0NzQwOTkxNDc1NzEyIiwiMjAyMTA0MjcxODA2MDg4MzY2NjQ1MzA0ODU1MTQyNDAiXSwib3JnSWRzIjpbIjIwMjIxMDE4MTAyNzU4MTAzMTg3NjI4ODYzOTU0NTM0NCJdLCJucyI6ImRlZmF1bHQiLCJsb2dpbkF0IjoxNjY3MzczNzkwNTgwLCJ1c2VybmFtZSI6InRlc3QwMDEifQ.knvnryA7qkgg_Yy-qYAVe8YjOO-KERWNLgBLvzW-6vc
            """.trimIndent()

        return headers
    }

    @Test
    fun 查询用户权限列表() {
        val path = "/api/userPer/getUserMenuPer"
        get(path, commonHeader())
    }

    @Test
    fun 角色列表() {
        val path = "/api/role/pageList"
        val body = """
            {"pageNum":1,"pageSize":10,"roleName":"","roleType":1,"regionLevel":5}
        """.trimIndent()
        post(path, body, commonHeader())
    }

    @Test
    fun 用户列表() {
        val path = "/api/user/listByPage"
        val body = """
            {"pageNum":1,"pageSize":10,"includeSub":1,"realname":"","userId":"20220402112841959776382941007872","userRole":"","dutyId":"","orgId":""}
        """.trimIndent()
        post(path, body, commonHeader())
    }

    @Test
    fun 重置密码() {
        val path = "/api/user/resetPassword?id=202211011511331037021086618882048"
        get(path, commonHeader())
    }

    @Test
    fun 删除用户() {
        val path = "/api/user/delete?id=202211011511331037021086618882048"
        get(path, commonHeader())
    }

    @Test
    fun 新增用户() {
        val path = "/api/user/add"
        val body = """
            {"username":"test001","realname":"test001","gender":"","orgIds":"202210181027581031876288639545344","adminLevel":"","adminPosition":"","dutyIds":"","roleIds":"","tel":"13277918809","fixedTelephone":"","email":"","userOrder":"1"}
        """.trimIndent()
        post(path, body, commonHeader())
    }


}