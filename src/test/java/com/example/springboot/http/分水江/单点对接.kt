package com.example.springboot.http.分水江

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 单点对接 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_FSJ_8080
        return ServerApi.DEV_FSJ_SMART_RIVER
    }

    override fun getToken(): String {
        return """
            eyJraWQiOiIyMDIxMDUwODEzNDE0MTg0MDU4NDI0NjI4MzM0MTgyNCIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2Iiwic2lkIjoiOGYxNWM2NWIwYmM0NDZiNmJmOGVlZjU3OWQ1MGViOTYifQ.eyJyb2xlSWRzIjpbIjIwMjEwNDI3MTgwNjA4ODM2NjY0NTMwNDg1NTE0MjQwIl0sIm9yZ0lkcyI6WyIyMDIyMTEwNDE0MzQyNjEwMzgwOTg5MDg2OTUzNzU4NzIiXSwibnMiOiJkZWZhdWx0IiwibG9naW5UeXBlIjowLCJyZXF1ZXN0SWQiOiIxODViN2M2Ni0yMzAwMiIsImxvZ2luQXQiOjE2OTk1MTIwMTk0NjksInVzZXJuYW1lIjoiYWRtaW4ifQ.WrBXIGQEDMociw_4eNfZC8K5qDL7L_F5OtKfcVczjNI
        """.trimIndent()
    }

    @Test
    fun 根据token获取用户信息() {
        val path = "/reservoir/sso/user"
        get(path)
    }
}