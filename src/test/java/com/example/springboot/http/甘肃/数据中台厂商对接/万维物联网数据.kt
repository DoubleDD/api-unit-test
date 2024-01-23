package com.example.springboot.http.甘肃.数据中台厂商对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 万维物联网数据:BaseTest() {
    override fun getServer(): ServerApi {
        return  ServerApi.PROD_THIRD
    }

    @Test
    fun iot(){
        var key = "iot-kafka-data:shuiku:buffer"
        val url = "/cache/value?key=$key"
        toFile(get(url))

    }
}