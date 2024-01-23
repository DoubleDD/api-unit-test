package com.example.springboot.http.甘肃.数据中台厂商对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

const val version = 43
const val sign = "d2c3a9128afa8f5cbdefc5eebbeba9c6"

class 华颖 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }


    @Test
    fun 统一数据接口() {
        val path = "http://data.ayiacloud.cn/service/retrieveData"
        val id = 1680
        val beginDate = "2022-08-25 17:00:00"
        val endDate = "2022-09-29 17:10:00"
        val body = """
            {
              "id": $id,
              "beginDate": "$beginDate",
              "endDate": "$endDate",
              "version": $version,
              "sign": "$sign"
            }
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 数据结构接口_所有项目列表() {
        val path = "http://data.ayiacloud.cn/service/queryByProjectMonitor"
        val body = """
           {
             "type": 1,
             "version": $version,
             "sign": "$sign"
           } 
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }


    @Test
    fun 数据结构接口_所有监测点() {
        val path = "http://data.ayiacloud.cn/service/queryByProjectMonitor"
        val project = "乔儿沟水库在线监测"
        val body = """
           {
             "type": 3,
             "project": "$project",
             "version": $version,
             "sign": "$sign"
           } 
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)

    }
}