@file:Suppress("LocalVariableName")

package com.example.springboot.http.甘肃.数据中台厂商对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType


class 张掖市金志水库 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.DEV_THIRD
    }
}

class 张掖市金志水库_NONE : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    private val appId = "9d81abc0350c42548ad6cc8456835b14"

    override fun getToken(): String {
        return "1828b28ea6cc481ba0d754f47e39d91c1659318869162"
    }

    @AfterEach
    override fun after() {
        super.after()
    }

    @Test
    fun 库水位库容() {
        val path = "http://39.99.255.56:8089/wearadayImpl/getResevoirSafetyDatas"
        val 水库编码 = "sk_004"
        val type = 1
        val body = """
            appid=${appId}&token=${token}&type=${type}&resevoirCode=${水库编码}
            """.trimIndent()
        post(path, body, MediaType.APPLICATION_FORM_URLENCODED)
    }
// 1662020414707
// 1662020414707
// 1662020414707
    @Test
    fun 位移() {
        val path = "http://39.99.255.56:8089/wearadayImpl/getResevoirSafetyDatas"
        val 水库编码 = "sk_001"
        val type = 2
        val body = """
            appid=${appId}&token=${token}&type=${type}&resevoirCode=${水库编码}
            """.trimIndent()
        post(path, body, MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 渗压渗流() {
        val path = "http://39.99.255.56:8089/wearadayImpl/getResevoirSafetyDatas"
        val 水库编码 = "sk_001"
        val type = 3
        val body = """
            appid=${appId}&token=${token}&type=${type}&resevoirCode=${水库编码}
            """.trimIndent()
        post(path, body, MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 气象数据() {
        val path = "http://39.99.255.56:8089/wearadayImpl/getResevoirSafetyDatas"
        val 水库编码 = "sk_001"
        val type = 4
        val body = """
            appid=${appId}&token=${token}&type=${type}&resevoirCode=${水库编码}
            """.trimIndent()
        post(path, body, MediaType.APPLICATION_FORM_URLENCODED)
    }

}