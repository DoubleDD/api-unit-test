package com.example.springboot.http.甘肃.cases

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.AES
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.net.URI
import java.net.URLEncoder
import java.util.regex.Pattern

class url重定向测试 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    @Test
    fun matcherTest() {
        val data =
            "https%3A%2F%2F60.13.54.71%3A30119%2Foa%2Fnhweb%2Fcommon%2Fview.html%3Fresourceid%3D596597"

        var r = Pattern.compile("http(s)?://60.13.54.71:30119/oa.*")
        var url = "https://60.13.54.71:30119/oa/nhweb/common/view.html?resourceid=596597"
        var matcher = r.matcher(url)
        var value = matcher.matches()
        assert(value)

        r = Pattern.compile("http[s]?://60.13.54.71:(30118|33238).*")
        url = "http://60.13.54.71:30118/#/home"
        matcher = r.matcher(url)
        value = matcher.matches()
        assert(value)

        url = "https://60.13.54.71:33238/#/login"
        matcher = r.matcher(url)
        value = matcher.matches()
        assert(value)
    }

    private val aes_secret = "1234567887654321"
    private val aes_secret_prod = "9a6329c45c4e5199943ed4418cb5d2ad"

    @Test
    fun decode() {
        val msg = "qYMPqRAzCC1jJtFaItrhqLZYMGeMNOiTlPKa/yEzBPVekJj92yNYiHj50jIoWrbk4pK0jUuX5FuneqGfUEK7Bg=="
        println(AES.decrypt(msg, aes_secret))
    }


    @Test
    fun test() {
        val msg = HashMap<String, String>()
        msg["username"] = "stx_01"
        msg["tel"] = "13277070001"
        msg["type"] = "1"
        val s = JSON.toJSONString(msg)
        println(s)
        val encrypt = AES.encrypt(s, aes_secret)
        println("encrypt = ${encrypt}")

        val targetUrl = "http://39.100.95.69:30102/"
        val url = "${getServer().server}/redirect/?url=${URLEncoder.encode(targetUrl, "utf-8")}&msg=${
            URLEncoder.encode(encrypt, "utf-8")
        }"
        println("${url}")
    }


    @Test
    fun decodeData() {
        val data = """
            http://60.13.54.71:30118/yunli/third/v1/redirect/?url=http%3A%2F%2F60.13.54.71%3A30118%2F&msg=IIOmjUhcVV%2BpbTUzc39UbYKggR0LQuBGnmcnep%2B65lKscHY5Pvgwinm01Q722yuzwnds5K%2BUpxQb6Wy9GJVvDQ%3D%3D
        """.trimIndent()
        val uri = URI.create(data)
        val map = buildParams(uri.query)
        val url = map.get("url")
        var encryptData = map.get("msg")
        println(url)
        encryptData = encryptData?.replace(" ", "+")
        println(AES.decrypt(encryptData, aes_secret_prod))


    }


    @Test
    fun 重定向测试(){
        var responseEntity =
            get("http://60.13.54.71:30118/yunli/third/v1/redirect/?url=http%3A%2F%2F60.13.54.71%3A30118%2F&msg=IIOmjUhcVV%2BpbTUzc39UbYKggR0LQuBGnmcnep%2B65lKscHY5Pvgwinm01Q722yuzwnds5K%2BUpxQb6Wy9GJVvDQ%3D%3D")
        println(responseEntity.headers.location)
    }
}