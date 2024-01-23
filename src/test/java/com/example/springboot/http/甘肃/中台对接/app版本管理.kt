package com.example.springboot.http.甘肃.中台对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test


class app版本管理 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    val midToken = """
        eyJraWQiOiI5NGYzOTYyYTYzODk2NjAzN2ZmYTlmN2Q3YzI2Y2RjMyIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2Iiwic2lkIjoiNTYyYzc5ZmViMGE3NDUxOWJkZTU2YjM5Njk0NzhkZjgifQ.eyJyb2xlSWRzIjpbIjIwMjIxMjA1MDAxODMzMTA0OTExNzUzOTM2NTQyOTI0OCIsIjIwMjIxMDE4MTYxNjUyMTAzMTk2NDA5MzY1MDcwNjQzMiIsIjIwMjIwNDE2MTk1NjM1OTY0OTc3NjI4OTIwNTUzNDcyIiwiOGY3ZjVhMzFkYTIxNDY3MDhmYjYwZTE2ZTA5NDFlNWEiLCI5OTA0OGE2MDYyNzk0YmMyOTU3YzcxZGQ2NmNkY2ZlYyJdLCJvcmdJZHMiOlsiODUzZDYzZWJiMjA3ZTY0ODQ2M2QxODZjMTI2MjUyNDEiXSwibnMiOiJkZWZhdWx0IiwibG9naW5BdCI6MTY4OTIxNDUzNDk0NiwidXNlcm5hbWUiOiJrZWRvbmcxIn0.WFr-5LL-Nk9IqKOABip2M1dEQMupyaf-SDJebPZouFA
    """.trimIndent()

    @Test
    fun 获取自定义页面配置() {
        val path = "http://60.13.54.71:30119/api/customForm/v1/selectCustomPageConfig?customPageId=143"
        val header = HashMap<String, String>()
        header["Cookie"] = "yunli-api-gateway-token=$midToken"
        get(path, header)
    }
}