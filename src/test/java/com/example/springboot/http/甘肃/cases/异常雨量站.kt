package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class portal : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_PORTAL
    }


    @Test
    fun 设置阈值() {
        val path = "/api/rainfall/station/abnormal/value"
        val v = 100
        post(path, "value=$v", MediaType.APPLICATION_FORM_URLENCODED)
    }
}