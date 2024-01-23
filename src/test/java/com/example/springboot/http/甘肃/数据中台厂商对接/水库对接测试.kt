package com.example.springboot.http.甘肃.数据中台厂商对接

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import java.io.File

class 水库对接测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_THIRD_DATA
        return ServerApi.PROD_THIRD_DATA_HTTPS
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
    fun 水库数据推送() {
        val path = "/data/sync/reservoir"
        val fileName =
            "/Volumes/code/com.gitee/spring-boot-components/spring-boot/src/test/java/com/example/springboot/http/甘肃/数据中台厂商对接/水库数据推送示例.json"
        val body = File(fileName).readText()
        // 取水口key
        // appKey：sXWyM2N16cA1H3MFn9Ce89bocoVPfKytYB
        //app secret： JEAkd04caYfpIbHdBHsuFoYi3GsMwy9feLA64JKTRBI392xkKEOTH3WJx91
        appKeySecretPair.set(Pair("sXWyM2N16cA1H3MFn9Ce89bocoVPfKytYB", "JEAkd04caYfpIbHdBHsuFoYi3GsMwy9feLA64JKTRBI392xkKEOTH3WJx91"))
        formatJson.set(true)
        val response = request(path, HttpMethod.POST, body, MediaType.APPLICATION_JSON)
        val json = JSON.parseObject(response.body)
        assert(json["code"] == 0)
    }
}