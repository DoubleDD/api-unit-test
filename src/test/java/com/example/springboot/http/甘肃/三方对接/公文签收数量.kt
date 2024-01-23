package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 公文签收数量 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.DEV_PORTAL
    }


    @Test
    fun test(){
        val url = "/OA/documentLZ"
        get(url)
    }
}