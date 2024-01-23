package com.example.springboot.http.分水江

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 文件上传 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    fun serverPrefix():String{
//        return "http://172.30.1.159:32626/api/water-resource"
        return "http://172.30.1.159:30037/api/flood-prevent"
//        return "http://localhost:8080"
    }

    @Test
    fun 文件上传测试() {
        formatJson.set(true)
        val file = "$USER_HOME/Documents/IntelliJIDEA_KeyMap.pdf"
        postFile("${serverPrefix()}/reservoir/common/upload/contingency", file)
    }
}