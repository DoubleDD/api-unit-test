package com.example.springboot.http.甘肃.三方对接.闫集水库

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.net.URLDecoder

class 闫集水库 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    override fun getToken(): String {
        return """
            123123123
        """.trimIndent()
    }

    //    var api = "http://localhost:9099/yanjishuiku"
    var api = "http://60.13.54.71:30119"

    @Test
    fun TestConnect() {
        val token = "123456"
        val path = "$api/TestConnect?token=$token&flag=123"
        get(path)
    }


    @Test
    fun uploadData() {
        val token = "123456"
        val path = "$server/UploadData?token=$token&datalist=${testData()}"
        get(path)
    }

    @Test
    fun data() {
        testData()
    }

    private fun testData(): String {
        val ss = """
            %7B%22Bat%22%3A%224.1V%22%2C%22McuId%22%3A902%2C%22Model%22%3A%22MCU-32%22%2C%22WatchTime%22%3A1591531800%2C%22datas%22%3A%5B%7B%22ch%22%3A1%2C%22chdata%22%3A%5B%7B%22dataid%22%3A1%2C%22sid%22%3A%22test1x%22%2C%22stype%22%3A%22ys%E5%80%BE%E6%96%9C%22%2C%22unit%22%3A%22%C2%B0%22%2C%22value%22%3A%220%22%7D%2C%7B%22dataid%22%3A2%2C%22sid%22%3A%22test1y%22%2C%22stype%22%3A%22ys%E5%80%BE%E6%96%9C%22%2C%22unit%22%3A%22%C2%B0%22%2C%22value%22%3A%220%22%7D%5D%7D%2C%7B%22ch%22%3A2%2C%22chdata%22%3A%5B%7B%22dataid%22%3A1%2C%22sid%22%3A%22test2x%22%2C%22stype%22%3A%22ys%E5%80%BE%E6%96%9C%22%2C%22unit%22%3A%22%C2%B0%22%2C%22value%22%3A%220%22%7D%2C%7B%22dataid%22%3A2%2C%22sid%22%3A%22test2y%22%2C%22stype%22%3A%22ys%E5%80%BE%E6%96%9C%22%2C%22unit%22%3A%22%C2%B0%22%2C%22value%22%3A%220%22%7D%5D%7D%2C%7B%22ch%22%3A3%2C%22chdata%22%3A%5B%7B%22dataid%22%3A1%2C%22sid%22%3A%22cj0%22%2C%22stype%22%3A%22ys%E7%A3%81%E5%B0%BA%22%2C%22unit%22%3A%22mm%22%2C%22value%22%3A%22290.174%22%7D%5D%7D%2C%7B%22ch%22%3A4%2C%22chdata%22%3A%5B%7B%22dataid%22%3A1%2C%22sid%22%3A%22cj1%22%2C%22stype%22%3A%22ys%E7%A3%81%E5%B0%BA%22%2C%22unit%22%3A%22mm%22%2C%22value%22%3A%22272.414%22%7D%5D%7D%2C%7B%22ch%22%3A5%2C%22chdata%22%3A%5B%7B%22dataid%22%3A1%2C%22sid%22%3A%22cj2%22%2C%22stype%22%3A%22ys%E7%A3%81%E5%B0%BA%22%2C%22unit%22%3A%22mm%22%2C%22value%22%3A%22257.268%22%7D%5D%7D%2C%7B%22ch%22%3A6%2C%22chdata%22%3A%5B%7B%22dataid%22%3A1%2C%22sid%22%3A%22cj3%22%2C%22stype%22%3A%22ys%E7%A3%81%E5%B0%BA%22%2C%22unit%22%3A%22mm%22%2C%22value%22%3A%22244.079%22%7D%5D%7D%2C%7B%22ch%22%3A7%2C%22chdata%22%3A%5B%7B%22dataid%22%3A1%2C%22sid%22%3A%22cj4%22%2C%22stype%22%3A%22ys%E7%A3%81%E5%B0%BA%22%2C%22unit%22%3A%22mm%22%2C%22value%22%3A%22297.502%22%7D%5D%7D%2C%7B%22ch%22%3A8%2C%22chdata%22%3A%5B%7B%22dataid%22%3A1%2C%22sid%22%3A%22cj5%22%2C%22stype%22%3A%22ys%E7%A3%81%E5%B0%BA%22%2C%22unit%22%3A%22mm%22%2C%22value%22%3A%2255.4287%22%7D%5D%7D%5D%2C%22factory%22%3A%22%E5%8D%97%E4%BA%AC%E5%B3%9F%E6%80%9D%E5%B7%A5%E7%A8%8B%E4%BB%AA%E5%99%A8%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%22%7D
        """.trimIndent()
        println(URLDecoder.decode(ss, "UTF-8"))
        return ss
    }
}