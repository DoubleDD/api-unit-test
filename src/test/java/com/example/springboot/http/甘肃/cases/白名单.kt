package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.*

class 白名单 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_THIRD
    }


    /**************************************** 签名 start ************************************************/
    @Test
    fun 签名_url_get() {
        val path = "/white/list/white-list:sign:url"
        get(path)
    }

    @Test
    fun 签名_url_add() {
        val path = "/white/list/white-list:sign:url"
        val url = "/api/formExam/**"
        val order = 0.0
        post(path, "urlReg=${url}&order=$order", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun 签名_url_remove() {
        val path = "/white/list/white-list:sign:url"
        val url = "/adapter/**"
        request(path, HttpMethod.DELETE, HttpEntity("urlReg=${url}"))
    }

    /**************************************** 签名 end ************************************************/





    /**************************************** token start ************************************************/
    @Test
    fun token_url_get() {
        val path = "/white/list/white-list:token"
        get(path)
    }

    @Test
    fun token_url_add() {
        val path = "/white/list/white-list:token"
        var url = "/wanwei/sftp/**"
        url = "/api/service/use/**"
        url = "/api/bs32/dataMiddleStatistics/**"
        url = "/algorthm/**"
        val order = 0.0
        post(path, "urlReg=${url}&order=$order", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun token_url_remove() {
        val path = "/white/list/white-list:token"
        val url = "/adapter/**"
        request(path, HttpMethod.DELETE, HttpEntity("urlReg=${url}"))
    }

    /**************************************** token end ************************************************/





    /**************************************** 验证码 start ************************************************/
    @Test
    fun 验证码_get() {
        val path = "/api/captcha/white/list"
        get(path)
    }

    @Test
    fun 验证码_add() {
        val path = "/api/captcha/white/list/"
        val appKey = "d82b982ad9cf42acb8334cf16ab24842"
        post(path, "appKey=$appKey", MediaType.APPLICATION_FORM_URLENCODED)
    }
    /**************************************** 验证码 start ************************************************/

}

class URL跳转白名单 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_THIRD
        return ServerApi.PROD_THIRD
    }

    /**************************************** url跳转 start ************************************************/
    @Test
    fun url跳转配置_get() {
        val path = "/white/list/redirect_auth_urls"
        get(path)
    }

    @Test
    fun url跳转配置_add() {
        val path = "/white/list/redirect_auth_urls"
        val order = 0.0
//        val url = URLEncoder.encode("http://60.13.54.71:30119(.)*", "utf-8")
        val url = URLEncoder.encode("http://60.13.54.71:30118(.)*", "utf-8")
        post(path, "urlReg=${url}&order=$order", MediaType.APPLICATION_FORM_URLENCODED)
    }

    /**************************************** url跳转 end ************************************************/

    @Test
    fun tttt() {
        val text =
            "JUU3JTk0JTk4JUU4JTgyJTgzJUU1JTg5JThEJUU3JUFCJUFGJUU5JUExJUI5JUU3JTlCJUFFJUU2JUIxJTg3JUU2JTgwJUJCJUU0JUI4JThFJUU4JUFGJUI0JUU2JTk4JThF"
        val base64text = Base64.getDecoder().decode(text)
        var decode =
            URLDecoder.decode(String(base64text))
        println(decode)
    }
}