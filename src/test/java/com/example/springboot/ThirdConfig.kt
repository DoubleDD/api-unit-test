package com.example.springboot

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.util.*

class ThirdConfig : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }


    @Test
    fun appConfig() {
        println("appKey: ${uuid()}")
        println("appSecret: ${uuid()+uuid()}")
    }

    private fun uuid() = UUID.randomUUID().toString().replace("-", "")
}