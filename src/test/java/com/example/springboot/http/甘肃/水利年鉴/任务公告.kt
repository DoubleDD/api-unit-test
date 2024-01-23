package com.example.springboot.http.甘肃.水利年鉴

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 任务公告 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_YEARBOOK
        return ServerApi.DEV_YEARBOOK
    }

    @Test
    fun 公告列表() {
        val args = "pageNum=1&pageSize=10&adminFlag=0&deptId=3e104403fd874510a4899ac2a5206f5e"
        val path = "/announcement/getList?$args"
        get(path)
    }

    @Test
    fun 公告详情() {
        val id = "20220218112452944192745285881856"
        val path = "/announcement/getDetail?id=$id"
        get(path)
    }
}