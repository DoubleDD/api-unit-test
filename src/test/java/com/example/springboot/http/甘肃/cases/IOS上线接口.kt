package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

class IOS上线接口 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_PORTAL
//        return ServerApi.DEV_PORTAL
//        return ServerApi.PROD_PORTAL
    }


    @Test
    fun ios() {
        get("/dict/clear/dict_items")
        val dictName = "APP_STORE_USER"
        val path = "/dict/map?dictName=${dictName}"
        get(path)
    }

    @Test
    fun 注销用户() {
        val path = "/api/user/c860e2786ed5ff58b5d638c2d39ba658"
        request(path, HttpMethod.DELETE, HttpEntity(""))

    }
}