package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 电子证照接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_THIRD
//        return ServerApi.LOCAL_THIRD
    }


    @Test
    fun test() {
        val path = "/token/getCardList?regionCode=620000"
        get(path)
    }



    @Test
    fun callAmountList() {
        val path = "/adapter/dzzz/basicApi/big-screen/call-amount-list"
        get(path)
    }



}