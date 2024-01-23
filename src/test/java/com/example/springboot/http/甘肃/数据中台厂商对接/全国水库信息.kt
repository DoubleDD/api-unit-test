package com.example.springboot.http.甘肃.数据中台厂商对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

var appid = "4110361dad2642dd9d9d2bb6240abe9b"

var secret = "dcb54abf367644e09a19421257ef2659"
class 全国水库信息 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }


    override fun getToken(): String {
        return """
            123
        """.trimIndent()
    }

    @Test
    fun 水库基础信息() {
        val path = ""
        val header = HashMap<String, String>()
        header["Authorization"] = "Bearer $token"
        get(path, header)
    }


}