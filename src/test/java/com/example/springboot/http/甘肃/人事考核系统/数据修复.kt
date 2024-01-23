package com.example.springboot.http.甘肃.人事考核系统

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 数据修复 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_AD
//        return ServerApi.PROD_AD
//        return ServerApi.PRE_PROD_AD
//        return ServerApi.DEV_AD
    }


    @Test
    fun 修复问卷状态() {
        post("/api/dataRepair/formExam", "", MediaType.APPLICATION_JSON)
    }

    @Test
    fun 数据检查() {
        get("/api/dataCheck/formExam")
    }

    @Test
    fun 设置公共模板() {
        val id = "20211124144437913077657636442112"
        get("/api/dataRepair/common/template?id=$id")
    }
}