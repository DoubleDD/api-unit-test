package com.example.springboot.http.甘肃.h3c

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class H3c : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_THIRD
//        return ServerApi.PROD_THIRD
    }

    @Test
    fun 获取告警列表() {
        val now = System.currentTimeMillis()
        val start = now - 24 * 60 * 1000
        val args = "startAlarmTime=$start&endAlarmTime=$now"
        val url = "/third/mock/list/alarm/test?$args"
        get(url)
    }

    @Test
    fun 告警任务() {
        val url = "/xxl-job/AlarmWarnJob"
        get(url)
    }
}