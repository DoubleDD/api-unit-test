package com.example.springboot.http.甘肃.智慧河湖

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 视频转码: BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    @Test
    fun 上传(){
        val path = "http://kd:9091/api/uav/uploadToFtp";
        postFiles(path,"/home/kedong/Videos/test2022-03-28_12.47.27-2022-04-26_06.49.57.mkv")
    }
}