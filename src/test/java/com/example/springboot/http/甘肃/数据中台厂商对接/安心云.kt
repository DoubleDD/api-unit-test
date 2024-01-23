package com.example.springboot.http.甘肃.数据中台厂商对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.apache.logging.log4j.util.Base64Util
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import java.net.URLEncoder

const val App_ID = "fe95166001b0a7f5e0006915b3d10a43bd38b568"
const val App_Secret = "d5b4cc8eff4848742d753f23875e60467592ca6a"
//const val App_ID = "85a896e1f890fd5ad4e7bb3d394800c4337c3bea"
//const val App_Secret = "be4a45ecfd5d986c612c8644c3178d9cae57f2ec"

class 安心云 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    override fun getToken(): String {
//         gaotai
        return "78cbb45f-8045-49dc-933a-1932ff9b8650"
        // shandan
//            return "1738f7bc-1206-4738-b4f1-30aa54875b68"
    }




    @Test
    fun auth2() {
        val path = "$anxinyun_server/oauth2/token"
        val authorization = Base64Util.encode("${App_ID}:${App_Secret}")
        val body = """
            grant_type=client_credentials
        """.trimIndent()
        val headers = HttpHeaders()
        headers["Content-Type"] = "application/x-www-form-urlencoded;charset=UTF-8"
        headers["Authorization"] = "Basic $authorization"
        post(path, body, headers)
    }


    @Test
    fun 监测因素() {
        val structureId = 3040
        val path = "$anxinyun_server/structures/${structureId}/factors?display&token=$token"
        get(path)
    }

    @Test
    fun 监测点() {
        val structureId = 3040
        val factorId = 463
        val path = "$anxinyun_server/structures/${structureId}/stations?factorId=$factorId&token=$token"
        get(path)
    }


    @Test
    fun 监测因素监测数据() {
        var stations = "52093"
        stations = "51573"
        val startTime = URLEncoder.encode("2022-10-10 00:00:00")
        val endTime = URLEncoder.encode("2022-10-10 22:00:00")

        val path =
            "$anxinyun_server/stations/theme/data?stations=${stations}&startTime=${startTime}&endTime=${endTime}&token=$token"
        get(path)
    }

    @Test
    fun aa(){
        val endTime = URLEncoder.encode("2022-08-17 00:05:00")
        println(endTime)
    }

    companion object {
        const val anxinyun_server = "https://openapi.anxinyun.cn/api/v1"
    }

}

