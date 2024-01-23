package com.example.springboot.http.福建

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

class 巡河 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_FUJIAN_NEW_JAVA
//        return ServerApi.DEV_FUJIAN_NEW_JAVA
    }


    @Test
    fun 巡河上报() {
        var path = "/api/river/save"
//        path = "/newAPI/events/v2.0/patrols/journals"
        val body = """
            {
              "channelId": "350000000008",
              "endTime": 1675242079,
              "flag": "102",
              "latlngArry": [
                {
                  "lati": 39.921209481533964,
                  "long": 116.33638622910114,
                  "time": "1663313672"
                },
                {
                  "lati": 39.92118750817178,
                  "long": 116.33637226283531,
                  "time": "1663313682"
                },
                {
                  "lati": 39.921162569859256,
                  "long": 116.33633933896701,
                  "time": "1663313697"
                },
                {
                  "lati": 39.92114265341573,
                  "long": 116.3362944410249,
                  "time": "1663313743"
                },
                {
                  "lati": 39.92112865031737,
                  "long": 116.33629643837034,
                  "time": "1663313773"
                },
                {
                  "lati": 39.92111666736208,
                  "long": 116.33628745979603,
                  "time": "1663313877"
                },
                {
                  "lati": 39.921105910631624,
                  "long": 116.33615575330538,
                  "time": "1663314017"
                },
                {
                  "lati": 39.92097396762337,
                  "long": 116.33612783198267,
                  "time": "1663314037"
                }
              ],
              "recordId": "575a45a8390c473e87383579e64d2229",
              "startTime": 1675241994,
              "userId": "49740"
            }
        """.trimIndent()
        post(path, body, tokenHeader())
    }


}

fun tokenHeader(): HttpHeaders {
    val header = HttpHeaders()
    header["x-hzz-token"] = """
        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVzSW4iOjE2NzMwODcyNzcsInBhc3N3b3JkIjoiVTFCVVpYSkFJekE1TURFPSIsImFyZWFOYW1lIjoi56aP5bu655yBIiwiYXBwVHlwZSI6ImFuZHJvaWQiLCJhcHBJZCI6IjEwMDAxMDAwIiwidHlwZSI6IjIiLCJleHAiOjE2NzMwODcyNzcsInVzZXJJZCI6IjQ5NzM5IiwiYXJlYWNvZGUiOiIzNTAwMDAwMDAwMDAiLCJpYXQiOjE2NzIyMjMyNzcsInVzZXJuYW1lIjoieHR6YzAxIn0.tegdU6KbZgDuvbk9LDI-mBrLurkWyg3X_dRXvdOkAY4
    """.trimIndent()
    header["content-type"] = "application/json;charset=UTF-8"
    return header
}