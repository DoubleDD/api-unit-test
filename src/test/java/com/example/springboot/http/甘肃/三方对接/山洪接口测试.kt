package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

class 山洪接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
    }


    @AfterEach
    override fun after() {
        super.after()
        val body = (response.body)
        assert(body.startsWith("{") && body.endsWith("}"))
    }

    /**
     * 导出
     * http://kd:9090/card/export/equipOnline?etime=2021-11-08+16:54:54&stime=2021-11-08+00:00:00&sttp=&comments=山洪测站&usfl=&_t=1636362538294&token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6OTQxLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNWNiNjkyNTFkNjQzNGVmMGJiNDc2MDQ3MmE4N2VhMmQiLCJpYXQiOjE2MzcyMjk2NzAsInVzZXJuYW1lIjoia2Vkb25nMSJ9.j6kEDaq3fPlrCNa4Q0N67bESN-hkAAuFYTmoqOovWJ4
     */
    @Test
    fun 山洪监测点在线率() {
        val path =
            "/adapter/sh/shanhong-gansu-slsh/equipOline/listForEquipOnline?etime=2021-11-08+16:54:54&stime=2021-11-08+00:00:00&sttp=&comments=山洪测站&usfl=&_t=1636362538294"
        get(path)
    }

    override fun getToken(): String {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6OTQxLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNWNiNjkyNTFkNjQzNGVmMGJiNDc2MDQ3MmE4N2VhMmQiLCJpYXQiOjE2MzcyMjk2NzAsInVzZXJuYW1lIjoia2Vkb25nMSJ9.j6kEDaq3fPlrCNa4Q0N67bESN-hkAAuFYTmoqOovWJ4"
    }
}

class token激活 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    override fun getToken(): String {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6OTUzLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiMWVjNzNmZjZiOGQ4NDE3NWJiZTRiY2JkODk4ZWNiMTgiLCJpYXQiOjE2Mzc3MjUxNzIsInVzZXJuYW1lIjoia2Vkb25nMSJ9.RMGXLDookSD8Wlkn-FznHhUJw6qBOlHZvQzHD0ShOI4"
    }

    @Test
    fun 激活() {
        val url = "http://60.13.54.71:30119/shanhong-gansu-slsh/user/getUserInfoBytoken"
        val headers = HttpHeaders()
        headers["Accept"] = "application/json, text/plain, */*"
        headers["Content-Type"] = "application/x-www-form-urlencoded; charset=UTF-8"
        headers["token"] = token
        headers["Cookie"] = "token=$token"
        headers["Referer"] = "http://60.13.54.71:30119/shanhong-gansu_slsh_web/?token=$token"
        headers["User-Agent"]="Mozilla/5.0 (X11; Linux x86_64; rv:94.0) Gecko/20100101 Firefox/94.0"
        post(url, "token=$token", headers)
    }
}