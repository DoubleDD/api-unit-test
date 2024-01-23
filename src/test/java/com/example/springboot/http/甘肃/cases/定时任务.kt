package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 定时任务 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    @Test
    fun 刷新云粒token(){
        get("http://localhost:9090/xxl-job/RefreshYunliToken")
    }
}