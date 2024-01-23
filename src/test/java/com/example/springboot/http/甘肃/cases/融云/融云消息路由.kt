package com.example.springboot.http.甘肃.cases.融云

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 融云消息路由 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.LOCAL_THIRD
    }

    //    http://localhost:8080/im/router/receive/message
    @Test
    fun 群聊() {
        val fromUserId = "4c92c477d018dd21b77b2f3acce1ce67"
        val toUserId = "VHivtuaOTM4nXnNkVMmL-g"
        val objectName = "RC:TxtMsg"
        val content = """
            {
              "content":"Hello world!",
              "user":
              {
                "id":"4242",
                "name":"Robin",
                "portrait":"http://example.com/p1.png",
                "extra":"extra"
              },
              "extra":""
            }

        """.trimIndent()
        val channelType = "GROUP"
        val msgTimestamp = "123"
        val msgUID = "123123123"
        val sensitiveType = 1
        val source = "pc"
        val args = """
           fromUserId=$fromUserId&toUserId=$toUserId&objectName=$objectName&content=$content&channelType=$channelType&msgTimestamp=$msgTimestamp&msgUID=$msgUID&sensitiveType=$sensitiveType&source=$source
        """.trimIndent().trim()
        val path = "/im/router/receive/message"
        post(path, args, MediaType.APPLICATION_FORM_URLENCODED)
    }
    @Test
    fun 单聊() {
        val fromUserId = "4c92c477d018dd21b77b2f3acce1ce67"
        val toUserId = "94f3962a638966037ffa9f7d7c26cdc3"
        val objectName = "RC:TxtMsg"
        val content = """
            {
              "content":"Hello world!",
              "user":
              {
                "id":"4242",
                "name":"Robin",
                "portrait":"http://example.com/p1.png",
                "extra":"extra"
              },
              "extra":""
            }

        """.trimIndent()
        val channelType = "PERSON"
        val msgTimestamp = "123"
        val msgUID = "123123123"
        val sensitiveType = 1
        val source = "pc"
        val args = """
           fromUserId=$fromUserId&toUserId=$toUserId&objectName=$objectName&content=$content&channelType=$channelType&msgTimestamp=$msgTimestamp&msgUID=$msgUID&sensitiveType=$sensitiveType&source=$source
        """.trimIndent().trim()
        val path = "/im/router/receive/message"
        post(path, args, MediaType.APPLICATION_FORM_URLENCODED)
    }
}