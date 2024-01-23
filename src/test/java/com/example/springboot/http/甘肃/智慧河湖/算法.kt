package com.example.springboot.http.甘肃.智慧河湖

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 算法 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }
    @BeforeEach
    override fun setUp() {
        super.setUp()
        formatJson.set(true)
    }


    val api = "http://60.13.54.71:30119/zhhh"
//    val api = "http://localhost:9090/adapter/zhhh"
//    val api = ""

    @Test
    fun 无人机算法开始() {
        val path = "/uav/algo/ctrl/start"
//        val path = "/adapter/zhhh/uav/algo/ctrl/start"
        val filename = arrayOf("/Users/kedong/Downloads/无人机视频/2021.05.05 10点-黄河永靖段-三塬镇巡河4.mp4")
        val cruiseName = "巡航接口测试5"
        val map = HashMap<String, String>()
        map["cruiseName"] = cruiseName
        postFiles("$api$path", "videoFile", null, map, filename)
    }


    @Test
    fun 无人机算法进度() {
        val id = "1667031806140100609"
        val path = "/uav/algo/ctrl/progress/$id"
//        val path = "/adapter/zhhh/uav/algo/ctrl/progress/$id"
        get("$api$path")
    }

    @Test
    fun 无人机算法结束() {
        val id = "1664586075726929921"
//        val path = "/adapter/zhhh/uav/algo/ctrl/stop/$id"
        val path = "/uav/algo/ctrl/stop/$id"
        post("$api$path", null, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 无人机算法结果() {
        val id = "1674335445946871809"
        val path = "/uav/algo/ctrl/result/$id"
//        val path = "/adapter/zhhh/uav/algo/ctrl/result/$id"
        get("$api$path")
    }

    // http://60.13.54.71:30119/zhhh/file/1674335445946871809/2023_06_29/08_34_19/e07f5ba9240f45ee9d10e4ad49042cf7.mp4

}