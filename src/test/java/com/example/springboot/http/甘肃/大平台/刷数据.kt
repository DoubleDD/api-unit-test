package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 刷数据:BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_PORTAL
    }

    @Test
    fun 管理员绑定默认角色(){
        val path = "admin/default/role"

    }
}