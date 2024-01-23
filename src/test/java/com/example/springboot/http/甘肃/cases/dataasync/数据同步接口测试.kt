package com.example.springboot.http.甘肃.cases.dataasync

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.apache.commons.lang.time.DateFormatUtils
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import java.io.File
import java.util.*

class 数据同步接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_THIRD
        return ServerApi.DEV_THIRD
//        return ServerApi.PROD_THIRD_HTTPS
    }

    @AfterEach
    override fun after() {
        super.after()
        val body = response.body
        val json = JSON.parseObject(body)
        assert(json["code"] == 0)
    }

    @Test
    fun 覆盖同步(){
        val path= "/data/sync/"
        val fileName = "src/test/java/com/example/springboot/http/cases/数据同步接口数据样本.json"
        val body = File(fileName).readText()
        request(path, HttpMethod.POST, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 数据同步() {
        val path = "/data/sync/"
        val body = """
            { "rows":[ { "fields":[ { "name":"create_time", "value":"2022-04-28 12:40:50" }, { "name":"update_time", "value":"2022-04-28 12:40:50" }, { "name":"content", "value":"test001" } ] }, { "fields":[ { "name":"create_time", "value":"2022-04-28 12:40:50" }, { "name":"update_time", "value":"2022-04-28 12:40:50" }, { "name":"content", "value":"test002" } ] } ], "tableName":"data_sync" }
        """.trimIndent()
        request(path, HttpMethod.POST, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 数据同步1() {
        val path = "/data/sync/"
//        /home/kedong/com.gitee/spring-boot-components/spring-boot/src/test/java/com/example/springboot/http/cases/数据同步接口数据样本.json
        val fileName = "/home/kedong/com.gitee/spring-boot-components/spring-boot/src/test/java/com/example/springboot/http/cases/数据同步接口数据样本.json"
        val body = File(fileName).readText()
        val response = request(path, HttpMethod.POST, body, MediaType.APPLICATION_JSON)
        val json = JSON.parseObject(response.body)
        assert(json["code"] == 0)
    }


    @Test
    fun 注册数据源() {
        val path = "/data/sync/datasource"
        val appey = "b3592e1c78e04077a0f9e0834e79e925"
        @Language("JSON") val config = """
{
  "username": "root",
  "password": "1@#qazxcde32wsX",
  "url": "jdbc:mysql://10.62.210.180:3307/eco_multiplex_adaptersite?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true"
}
        """.trimIndent()
        post(path, "appKey=$appey&config=$config", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 更新数据() {
        val path = "/data/sync/"
        val date = DateFormatUtils.format(Date(), "yyyy-MM-dd HH:mm:ss")
        @Language("JSON") val body = """
            {
              "rows": [
                {
                  "fields": [
                    {
                      "name": "create_time",
                      "value": "$date"
                    },
                    {
                      "name": "update_time",
                      "value": "$date"
                    },
                    {
                      "name": "content",
                      "value": "接口更新数据测试"
                    }
                  ],
                  "id": "35"
                },
                {
                  "id": "57",
                  "fields": [
                    {
                      "name": "create_time",
                      "value": "$date"
                    },
                    {
                      "name": "update_time",
                      "value": "$date"
                    }
                  ]
                }
              ],
              "tableName": "data_sync"
            }
        """.trimIndent()
        request(path, HttpMethod.PUT, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 删除数据() {
        val path = "/data/sync/"
        @Language("JSON") val body = """
            {
              "rows": [
                {
                  "id": "33"
                },
                {
                  "id": "b74eddf7adc2487bb2314654f39ad4de"
                }
              ],
              "tableName": "qs_apply_basic"
            }
        """.trimIndent()
        request(path, HttpMethod.DELETE, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun test(){
        get("/data/sync/progress/123")
    }
}