package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 视频 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.DEV_THIRD
//        return ServerApi.LOCAL_THIRD
    }

    override fun withFeign(): Boolean {
        return false
    }

    @Test
    fun list() {
        val path = "/api/video/monitor/list?keyword=中山黄河铁桥北滨河路侧向北30米东侧"
        get(path)
    }

    @Test
    fun replay() {
        appKeySecretPair.set(Pair("B1F6385B44003CB192A8A6D28C18FE79", "303B77F0C910E99CA329517D075F5CD7"))
        val path = "/api/video/monitor/realplay?stationCode=62000000581314100716"
        get(path)
    }
}