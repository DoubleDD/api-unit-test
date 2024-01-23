package com.example.springboot.http.定时任务

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 全文检索 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_FS;
//        return ServerApi.PRE_PROD_FS;
//        return ServerApi.LOCAL_FS;
    }

    @Test
    fun 刷新es索引() {
        val job = "GlobalSearchRefactorJob"
        var args = "WI_UNIT"

        /**
         * 淤地坝
         */
//        args = "AB"
        /**
         * 提防
         */
//        args = "DIKE"
        /**
         * 泵站
         */
//        args = "PUMP"
        /**
         * 水闸
         */
//        args = "GATE"
        /**
         * 引调水工程
         */
//        args= "AW"
        /**
         * 灌区
         */
//        args="WR_IRR"
        /**
         * 农村供水工程
         */
//        args="WR_CWS"
//        args=""
        get("/xxljob/$job?args=$args")
    }

}