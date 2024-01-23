package com.example.springboot.http.甘肃.登陆服务

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class OA退出登录 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    @Test
    fun OAlogout() {
        val url = "http://60.13.54.71:30119/oa/api/openApi/v1/auth/logout"
        val body: MutableMap<String, String> = HashMap()
        body["loginid"] = "kedong1"
        body["timestamp"] = System.currentTimeMillis().toString() + ""

        post(url, JSON.toJSONString(body), MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    }


}

class OA接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.DEV_PORTAL
//        return ServerApi.PRE_PROD_PORTAL
    }

    override fun getToken(): String {
       return """
           eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NTIxLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNzk5ZGYxZDFlOGNhNDk2YmE5MmNlZjVmZTczOGZlYmYiLCJpYXQiOjE2NzU3NjQzMDMsInVzZXJuYW1lIjoia2Vkb25nMSJ9.b999NV57HI-zVqKLa1x5YTEib11-dg0BvqeM01UjyB0
       """.trimIndent()
    }

    @Test
    fun 会议列表() {

        val path = "/OA/meeting/getMeetingInfo"
        val body = """
            {"meetingStartdatefrom":"2021-04-26 00:00:00","meetingStartdateto":"2021-05-02 23:59:59","timeSag":6,"userContain":"田大永","usertype":2}
        """.trimIndent()

        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 查询OA公文信息() {
        val path = "/OA/flow/listAllOAFlowList?userId=kedong1&type=2"
        post(path, "", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 查询OA公文数量() {
        get("/OA/documentLZ")
    }

}