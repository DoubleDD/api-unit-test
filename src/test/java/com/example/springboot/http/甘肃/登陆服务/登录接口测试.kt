package com.example.springboot.http.甘肃.登陆服务

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.example.springboot.http.common.AES
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.*
import java.net.URLEncoder

class 登录接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_THIRD
//        return ServerApi.PROD_THIRD
//        return ServerApi.PRE_PROD_THIRD
        return ServerApi.DEV_THIRD
    }


    @Test
    fun login() {
        val path = "/api/auth/login"
        val body = HashMap<String, String>()
        body["username"] = "kedong1"
//        body["username"] = "liudandan_s"
//        body["passwd"] = "4606536168acfbd92da3d93e5679ad9dbc257de3"
//        body["passwd"] = "04341175a71bea3753994165ccad796106e50dda"
//        body["passwd"] = getSha1("gssl@123456")
//        EDEJKpVd+ocK8qEPWfp2AtWgoR9N9sJAfzriUtlvXK0=
        body["passwd"] = getSha1("kedong@123")
        body["authType"] = "0"

        val header = HttpHeaders()
        header["Content-Type"] = "application/x-www-form-urlencoded"

        request(path, HttpMethod.POST, HttpEntity(getFormUrlencoded(body), header))
    }


    @Test
    fun login1() {
        val path = "/api/auth/login"
        val body = HashMap<String, String>()
        body["username"] = "stx_01"
        body["passwd"] = getSha1("hzz@Yunli1234")
        body["authType"] = "0"

        val header = HttpHeaders()
        header["Content-Type"] = "application/x-www-form-urlencoded"

        request(path, HttpMethod.POST, HttpEntity(getFormUrlencoded(body), header))
    }
}

class uniauth登录接口 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_UN
//        return ServerApi.PROD_UN
//        return ServerApi.LOCAL_UN
//        return ServerApi.DEV_UN
    }

    var newToken: String = ""

    @BeforeEach
    override fun setUp() {
        super.setUp()
    }

    override fun withFeign(): Boolean {
        return true
    }

    override fun getToken(): String {

//        eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI5MmMwMjE0YTNjNWY0NmU0OWJmZTMwNDIxNWY5NDljMyIsInJhbmRvbSI6NzM2LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiZTY3YTIzNjY3MDA2NDYxNThkYmFkZGNhNGQzMWFmYTkiLCJ1dUlkIjoiY2ExNDUzYmExNGYzNGFhOWI1ZDgxZTU1NDVkNDBhOGYiLCJpYXQiOjE2ODY2MzcyMjAsInVzZXJuYW1lIjoiemhhb3lpbmdkb25nIn0.Z_IpviV-l58gGdrqLcN7XMoPTjHw_Pwxg_T_aVd9AWQ
        return """
        """.trimIndent()
    }

    val rongkey = "T87mK5okVNEIFLIVGC2jxybDDRgPgbRX"

    @Test
    fun ioc登陆() {
        val path = "/mid/auth/ioc"
        get(path)
    }

    @Test
    fun 登录() {
        val path = "/uniauth/auth/login"
        val username = "kedong1"
        val password = "kedong@123"
//        val username = "ssltyszh"
//        val password = "@gszhsl2023@"
        val data = """
            {"userName":"$username","passwd":"${
            AES.PKCS5Padding.encrypt(
                password,
                rongkey
            )
        }","authType":0,"encryptionFlag":0}
        """.trimIndent()

        val header = HttpHeaders()
        header["Content-Type"] = "application/json"
//        header["captcha"] = "4b72e68a1f8143d281b1663fe54371fb.1234"
        header["client"] = "kVNEIFLIVGC2jxybDDRg"
        header["Referer"] = "http://60.13.54.71:30119/yl-pre"

        val response: ResponseEntity<String> = request(path, HttpMethod.POST, HttpEntity(data, header))
        val headers = response.headers
        println("响应头：")
        println(JSON.toJSONString(headers))
        println()
        println("Cookies:")
        for (header1 in headers) {
            if ("Set-Cookie".equals(header1.key)) {
                for (v in header1.value) {
                    println(v)
                }
            }
        }
        println()
        val responseBody = response.body
        println(responseBody)
        val json: JSONObject = JSON.parseObject(responseBody)
        val result = json.getJSONObject("result")
        newToken = result.get("token") as String
        println(newToken)
    }

    @Test
    fun app登录() {
        val path = "/uniauth/auth/app/login"
        var username = "kedong1"
        var password = URLEncoder.encode(AES.PKCS5Padding.encrypt("kedong@123", rongkey))
        username = "hekaixuan"
        password = URLEncoder.encode(AES.PKCS5Padding.encrypt("Gs#3YiTq@aNQCem", rongkey))
        username = "caiwenji"
        password = URLEncoder.encode(AES.PKCS5Padding.encrypt("Gs#3YiTq@aNQCem", rongkey))
        val cid = "123"
        val data = """
           username=$username&passw=$password&cid=$cid
        """.trimIndent()

        val header = HttpHeaders()
        header["Content-Type"] = "application/x-www-form-urlencoded"
        header["feign"] = "2D74F561-0D80-2197-FD26-F0FAC2BFC7CD"
        header["client"] = "kVNEIFLIVGC2jxybDDRg"

        formatJson.set(true)
        request(path, HttpMethod.POST, HttpEntity(data, header))

    }

    @AfterEach
    override fun after() {
    }


    @Test
    fun 退出登录() {
        val path = "/uniauth/auth/logout"

        post(path, "", MediaType.APPLICATION_FORM_URLENCODED)
    }

    @Test
    fun token校验() {
        val path = "/uniauth/auth/check?token=$token"
        get(path)
    }

    @Test
    fun 根据token获取用户信息() {
        for (c in 1..25) {
            登录()
            for (i in 1..7) {
                val path = "/uniauth/auth/getUserInfoByToken?token=$newToken"
                val headers = HttpHeaders()
                headers["token"] = newToken
                get(path, headers)
            }
        }
    }
    @Test
    fun 通过token获取用户信息(){
        登录()
        val path = "/uniauth/auth/getUserInfoByToken?token=$newToken"
        val headers = HttpHeaders()
        headers["token"] = newToken
        get(path, headers)
    }

    @Test
    fun ywToken() {
        get("/uniauth/auth/yw/toke")
    }

    @Test
    fun 用户列表() {
        post("/uniauth/account/queryAccountPageList", "{}", MediaType.APPLICATION_JSON)
    }


    @Test
    fun cim() {
        get("/sso/cim?token=$token")
    }
}