package com.example.springboot.http.甘肃.消息

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import java.net.URLEncoder

class WebSocket : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_MESSAGE
//        return ServerApi.INNER_MESSAGE
    }

    @Test
    fun 发消息() {
        val path = "/yearbook/file/send/message"
        val targetId = "kedong1"
        val msg = mapOf(
            "fromUserId" to "kedong1",
            "toUserId" to "kedong1",
            "content" to "接口消息测试",
            "messageType" to 1,
        )

        post(
            path, """
            targetId=$targetId&msg=${URLEncoder.encode(JSON.toJSONString(msg), "UTF-8")}
        """.trimIndent(), MediaType.APPLICATION_FORM_URLENCODED
        )
    }


    @Test
    fun 水利年鉴ws消息() {
        val path = "/yearbook/message"
        val msg = mapOf(
            "cmd" to "yearbook_file_zip_progress",
            "payload" to mapOf(
                "uuid" to "4984bb3a-0497-43ec-b708-0bb81554026f"
            ),
        )

        post(
            path, """
            message=${URLEncoder.encode(JSON.toJSONString(msg), "UTF-8")}
        """.trimIndent(), MediaType.APPLICATION_FORM_URLENCODED
        )
    }

//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------

    @Test
    fun 给客户端发消息() {
        val path = "/ws/message/send"

        val namespace = "portal"
        val targetId = "kedong1"
        val msg = mapOf(
            "cmd" to "TODO_STATISTIC_REFRESH",
        )

        post(
            path, """
                namespace=$namespace&targetId=$targetId&msg=${URLEncoder.encode(JSON.toJSONString(msg), "UTF-8")}
        """.trimIndent(), MediaType.APPLICATION_FORM_URLENCODED
        )
    }

    @Test
    fun 给服务端发消息() {
        val path = "/ws/message/client/test?namespace=demoData&message=demoData"
        val msg = mapOf(
            "cmd" to "yearbook_file_zip_progress",
            "payload" to mapOf(
                "uuid" to "4984bb3a-0497-43ec-b708-0bb81554026f"
            ),
        )

        post(
            path, """
            message=${URLEncoder.encode(JSON.toJSONString(msg), "UTF-8")}
        """.trimIndent(), MediaType.APPLICATION_FORM_URLENCODED
        )
    }
}