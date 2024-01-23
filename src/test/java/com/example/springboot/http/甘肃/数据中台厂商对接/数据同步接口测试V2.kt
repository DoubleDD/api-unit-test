package com.example.springboot.http.甘肃.数据中台厂商对接

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

class 数据同步接口测试V2 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_THIRD_DATA
//        return ServerApi.PROD_THIRD_DATA
//        return ServerApi.DEV_THIRD_DATA
    }

    @AfterEach
    override fun after() {
        super.after()
        val body = response.body
        val json = JSON.parseObject(body)
        assert(json["code"] == 0)
    }

    @Test
    fun 数据同步() {
        val path = "/data/sync/v2"
        val fileName =
            "/home/kedong/com.gitee/spring-boot-components/spring-boot/src/test/java/com/example/springboot/http/数据中台厂商对接/取水口数据推送示例.json"
        val body = File(fileName).readText()
        // 取水口key
        appKeySecretPair.set(Pair("9b1deb4d92fd4b9daf2b922f27342452", "71e0af0617564c1cba164d300bd439d4"))
        formatJson.set(true)
        val response = request(path, HttpMethod.POST, body, MediaType.APPLICATION_JSON)
        val json = JSON.parseObject(response.body)
        assert(json["code"] == 0)
    }


    @Test
    fun 全量数据同步() {
        val path = "/data/sync/full"
        val fileName =
            "/home/kedong/com.gitee/spring-boot-components/spring-boot/src/test/java/com/example/springboot/http/数据中台厂商对接/取水口数据推送示例.json"
        val body = File(fileName).readText()
        // 取水口key
        appKeySecretPair.set(Pair("9b1deb4d92fd4b9daf2b922f27342452", "71e0af0617564c1cba164d300bd439d4"))
        formatJson.set(true)
        val response = request(path, HttpMethod.POST, body, MediaType.APPLICATION_JSON)
        val json = JSON.parseObject(response.body)
        assert(json["code"] == 0)
    }


    @Test
    fun 注册数据源() {
        val path = "/data/sync/datasource"
        val appey = "9b1deb4d92fd4b9daf2b922f27342452"
        @Language("JSON") var config = """
{
  "username": "root",
  "password": "1@#qazxcde32wsX",
  "url": "jdbc:mysql://10.62.210.180:3307/eco_multiplex_adaptersite?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true"
}
        """.trimIndent()
        config = """
           {
              "username": "root",
              "password": "1@#qazxcde32wsX",
              "url": "jdbc:mysql://10.62.210.180:3307/iot?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true"
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
    fun test() {
        get("/data/sync/progress/123")
    }
}