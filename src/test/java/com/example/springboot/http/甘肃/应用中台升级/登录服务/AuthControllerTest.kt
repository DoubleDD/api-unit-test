package com.example.springboot.http.甘肃.应用中台升级.登录服务

import com.alibaba.fastjson.JSONObject
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class AuthControllerTest : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.DEV_UN_UPDATE
//        return ServerApi.LOCAL_UN
//        return ServerApi.DEV_UN
        return ServerApi.PROD_UN
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NjI0LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiM2Y5MzI2YjZhYWUwNDYzZGFhZDRiMjFiYWMyOGE0NWUiLCJpYXQiOjE2NzY4NTgxNzAsInVzZXJuYW1lIjoia2Vkb25nMSJ9.NGG-9umAIeURMQGD3ytRrtSd6OFxnGKSe2UNLoDQ1T0
        """.trimIndent()
    }

    @Test
    fun 根据token获取用户信息() {
        val path = "/uniauth/auth/getUserInfoByToken?token=$token"
        var get = get(path)
        println(get.body)
    }

    @Test
    fun 登录() {
        val path = "/uniauth/auth/userLogin"
        val loginVo = AccountLoginVo()
        loginVo.userName = "kedong1"
        loginVo.passwd = "4606536168acfbd92da3d93e5679ad9dbc257de3"

        post(path, JSONObject.toJSONString(loginVo), MediaType.APPLICATION_JSON)
    }

    @Test
    fun 退出登录() {
        val path = "/uniauth/auth/logout"
        val body = "token=$token"
        post(path, body, MediaType.APPLICATION_FORM_URLENCODED)
    }
}

class AccountLoginVo {
    //    @ApiModelProperty(value = "登录账户")
    var userName: String? = null

    //    @ApiModelProperty(value = "密码")
    var passwd: String? = null

    //    @ApiModelProperty(value = "密码传输时是否使用base64编码，1-是，0-否")
    var encryptionFlag = 0

    //    @ApiModelProperty(value = "认证方式，0-用户名密码，1-手机号密码，2-邮箱密码，3-身份证密码，4-证书", example = "0")
    var authType = 0

    //    @ApiModelProperty("扩展字段Map：若为证书认证，需提供签名")
    var exMap: Map<String, Any>? = null

}