package com.example.springboot.http.甘肃.应用中台升级.登录服务

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 登录 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
    }


    @Test
    fun 修改密码() {
        val path = "/api/data/center/syncPwd"
        //94f3962a638966037ffa9f7d7c26cdc3,kedong@123
        val args = "userId=94f3962a638966037ffa9f7d7c26cdc3&password=kedong@123"
        get("$path?$args")
    }
}