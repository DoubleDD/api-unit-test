package com.example.springboot.http.甘肃.智慧河湖

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.apache.commons.lang.time.DateFormatUtils
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import java.util.*

class Uavcontroller : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.PRE_PROD_SR
//        return ServerApi.DEV_SR
        return ServerApi.LOCAL_SR
    }

    @BeforeEach
    override fun setUp() {
        super.setUp()
        formatJson.set(true)
    }
//    eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NTUsImdyb3VwSWQiOiIwMTI0MTJkMmE3YzI0NjFlMTkzZjM2NzYzM2YxM2MxOCIsImFwcElkIjoiZ2Fuc3VfYXBwbHkiLCJ1c2VySWQiOiI5NGYzOTYyYTYzODk2NjAzN2ZmYTlmN2Q3YzI2Y2RjMyIsInV1SWQiOiJhYjFiYjJmZWI5MjM0ZTViODNmZTYwMjgyYzg4NzY5ZSIsImlhdCI6MTY4NjIzNTg3NiwidXNlcm5hbWUiOiJrZWRvbmcxIn0.GbMTBDuZgD0hxm5FlPO_gxjIxyIlTUtjNGg_r54bwRk

    override fun getToken(): String {
        return """
        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MjkxLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiMWQwYjIyOTJlZGIxNDljNjhjMjc2MDcwYTljYmViNDAiLCJpYXQiOjE2ODY3MzQyNzEsInVzZXJuYW1lIjoia2Vkb25nMSJ9.cArPExf_zTU1NzQppAx3QlHwtLdMaGd0PS2vNpUjBYQ
        """.trimIndent()
    }

    @Test
    fun 上传视频() {
        val path = "/api/uav/uploadToFtp";
        postFiles(path, "/Users/kedong/Downloads/掌上水利/无人机视频/20230605测试Trim.mp4")
    }

    @Test
    fun 上传轨迹() {
        val path = "/api/uav/uploadToFtp";
        postFiles(path, "/Users/kedong/Downloads/123.srt")
    }

    @Test
    fun 提交创建无人机视频分析任务() {
        val path = "/api/uav/creatUavAnalysisTask"
        val random = UUID.randomUUID()
        val date = DateFormatUtils.format(Date(), "yyyy-MM-dd HH:mm:ss")

        @Language("JSON")
        val body = """
            {
            "regionCode": 620403,
            "flightName": "20230605测试Trim",
            "flightDate": "$date",
            "flightTime": "1小时",
            "flightLength": 1.5,
            "videoUrl":"/smart-river/uav/5ac4bc321bdd46099fd1dfc58481ac63-20230605测试Trim.mp4"
            }
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    //            "srtUrl": "/smart-river/uav/b4f2c4603c6c4b5a8f2c1bdcade7c03a-123.srt"
    @Test
    fun isUavtaskcompelet() {
        val path = "/api/uav/isUavTaskComplete?token=$token"

        @Language("JSON")
        val body = """
            [
              {
                "taskId": "1682929683420819458"
              }
            ]
        """.trimIndent()
        formatJson.set(true)
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun getCache() {
        val path = "/api/uav/uav/cache"
        val body = """
            {"regionCode":"620403","startDate":"2021-01-01","endDate":"2023-12-31"}
        """.trimIndent()
        formatJson.set(true)
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun delCache() {
        val path = "/uav/cache/del"
        val taskId = "1666691500815335426"
        formatJson.set(true)
        request(path, HttpMethod.DELETE, "taskId=$taskId", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 获取详情() {
        val id = "1676854333410521089"
        get("/api/uav/queryUavFilght?id=$id")
    }

    @Test
    fun 保存分析结果() {
        val id = "1666820485947768833"
        get("/api/uav/save/result?id=$id")
    }

    @Test
    fun 生成pdf文件() {
        val id = "1668915192370696193"
        get("/api/uav/save/pdf?id=$id")
    }

    @Test
    fun 生成doc文件() {
        val id = "1682929683420819458"
        get("/api/uav/save/doc?id=$id")
    }


    @Test
    fun 左上角列表() {
        get("/api/uav/queryUavHomePage?type=1&regionCode=620403&startDate=2023-01-01&endDate=2023-12-31")
    }


}