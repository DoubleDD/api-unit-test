package com.example.springboot.http.泛生态产品

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.net.URLEncoder

class 文件上传 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_ONEMAP_MANAGER
    }


    @Test
    fun 文件上传测试() {
        formatJson.set(true)
        val file = "$USER_HOME/Documents/IntelliJIDEA_KeyMap.pdf"
        val dir = URLEncoder.encode("测试123", "UTF-8")
        postFile("/api/file/upload/${dir}?", file)
    }
}