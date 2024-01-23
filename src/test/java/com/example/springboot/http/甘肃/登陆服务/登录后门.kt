package com.example.springboot.http.甘肃.登陆服务

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 登录后门: BaseTest() {
    override fun getServer(): ServerApi {
       return ServerApi.PRE_PROD_THIRD
//       return ServerApi.DEV_THIRD
    }

    @Test
    fun 登录(){
        val username = "yangyezhou"
        val path = "/api/auth/home?username=$username&id=MDQzNDExNzVhNzFiZWEzNzUzOTk0MTY1Y2NhZDc5NjEwNmU1MGRkYQ"
        get(path)
    }

    @Test
    fun 修改数据中台密码(){
        val path = "/api/data/center/syncPwd"
        val userId = "71c08d9c7fb09cfa3cbbc6c38283437e"
        val password = "@gszhsl2023@"

    }
}