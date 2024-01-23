package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 卡片: BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.PRE_PROD_PORTAL
    }

    @Test
    fun 生产订阅数据(){
        get("/cardManage/dingyue")
    }
}