package com.example.springboot.http.甘肃.三方对接

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import java.util.*

class 待办事项接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_THIRD
        return ServerApi.DEV_THIRD
//        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.PROD_THIRD
    }

    @AfterEach
    override fun after() {
        val body = response.body
        val json = JSON.parseObject(body)
        assert(json["code"] == 0)
    }

    @Test
    fun 同步待办事项() {
        appKeySecretPair.set(
            Pair(
                "oa_abed4456fd750d00aa068dab5ee489ff",
                "ZTAzZDYzOTYwZTBkNDBiYzk0Zjc1MzI5NDIwNDE0MDE="
            )
        )
        val path = "/todo/sync"
        @Language("JSON") val debugBody = """
            {
              "todoUser": "daxiong",
              "businessId": "${UUID.randomUUID()}",
              "todoDate": 1652276781000,
              "createUser": "lanyali",
              "title": "测试统一待办----通知测试23",
              "content": "测试统一待办---通知测试23",
              "url": "http://10.62.210.28:8089/oa/openwork/message_info.jsp?requestid=650652&loginid=lixin",
              "status": 0,
              "subType": 1,
              "subTypeDesc": "test"
            }
        """.trimIndent()
        post(path, debugBody, MediaType.APPLICATION_JSON)
    }


    @Test
    fun 批量同步待办事项异常请求测试() {
        val path = "/todo/batch/sync"
        @Language("JSON") var debugBody = """
            []
        """.trimIndent()
        post(path, debugBody, MediaType.APPLICATION_JSON)
        debugBody = """
            [
              {
                "businessId": "123",
                "content": "",
                "createUser": "",
                "status": 1,
                "title": "批量推送测试1",
                "todoDate": 1651197755694,
                "todoUser": "kedong",
                "type": 1,
                "url": "http://10.18.60.27:8084/#/gsxf-web/qsxk/qsxksp?applyId=b74eddf7adc2487bb2314654f39ad4de&token="
              }
            ]
        """.trimIndent()
        post(path, debugBody, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 批量同步待办事项() {
        val path = "/todo/batch/sync"
        @Language("JSON") val debugBody = """
            [
              {
                "businessId": "df1334414057473ba5722f3bad91a30c",
                "content": "批量推送测试1",
                "createUser": "qsxk",
                "status": 1,
                "title": "批量推送测试1",
                "todoDate": 1651197755694,
                "todoUser": "kedong1",
                "type": 1,
                "url": "http://10.18.60.27:8084/#/gsxf-web/qsxk/qsxksp?applyId=b74eddf7adc2487bb2314654f39ad4de&token=",
                "subType": 1,
                "subTypeDesc": "test"
              },
              {
                "businessId": "df1334414057473ba5722f3bad91234234c",
                "content": "批量推送测试2",
                "createUser": "qsxk",
                "status": 1,
                "title": "批量推送测试2",
                "todoDate": 1651197755694,
                "todoUser": "kedong1",
                "type": 1,
                "url": "http://10.18.60.27:8084/#/gsxf-web/qsxk/qsxksp?applyId=b74eddf7adc2487bb2314654f39ad4de&token=",
                "subType": 1,
                "subTypeDesc": "test"
              }
            ]
        """.trimIndent()
        post(path, debugBody, MediaType.APPLICATION_JSON)
    }
}

class 待办事项大平台接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_PORTAL
//        return ServerApi.DEV_PORTAL
        return ServerApi.PROD_PORTAL
//        return ServerApi.PRE_PROD_PORTAL
    }

    //            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6Njc1LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNjE4OTY2MzVlMWY2NDVlY2ExOWJjZDMxMzAyNTEwOGMiLCJpYXQiOjE2Njg0OTk2MzAsInVzZXJuYW1lIjoia2Vkb25nMSJ9.wRf2NV1id4QCVITbo5Nu8PhEejASg7oKdXF4ulnwSYY
    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJiYjJkYWJlNjEzNWQ0ODY5YjEwYTg0N2JhNjhiOGIwOSIsInJhbmRvbSI6Njg5LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiNzRmN2YxOTA3OGQ0M2UyOWFiNDEyMWM1Y2VlMjU2YTciLCJ1dUlkIjoiNWQ2ODg0MDJkZDdkNDdkMzkzNmU0ODI5ZjRmNzIxYTMiLCJpYXQiOjE2Njg1MDAxNjEsInVzZXJuYW1lIjoidGVzdDAwMSJ9.RNsykF7-3GFXhTP7WYWj9jhCEi9OzEjU6WmxZmJUKlA
        """.trimIndent()
    }

    @Test
    fun todolist() {
        val path = "/api/todo/list"
        get("$path?pageNum=1&pageSize=20&type=1")
    }

    @Test
    fun statistic() {
        val path = "/api/todo/statistic"
        get("$path?isTodo=true&type=1")
    }

    @Test
    fun 角标() {
        get("/api/todo/cornerMark?username=kedong1")
    }
}



