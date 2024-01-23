package com.example.springboot.http.甘肃.水利年鉴

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 文件上传 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_YEARBOOK
    }


    @Test
    fun 文件上传测试() {
        val file = "/home/kedong/Downloads/皑皑.webp"
//        val file = "/home/kedong/Downloads/92Q888piCU9v.jpg-0.bmp"
        postFile("/announcement/uploadAnnouncement", file)
    }
}