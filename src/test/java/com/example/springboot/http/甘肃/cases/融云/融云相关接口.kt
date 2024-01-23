package com.example.springboot.http.甘肃.cases.融云

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.serializer.SerializerFeature
import com.example.springboot.http.common.AES
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import java.net.URLEncoder

val rongLoginKey = "T87mK5okVNEIFLIVGC2jxybDDRgPgbRX"

class 系统消息 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.PRE_PROD_PORTAL
    }

    @Test
    fun 发送系统消息() {
        val path = "/api/imtools/system/notification"
        val content = "系统消息测试——notification"
        val userId = ""
        val username = "kedong1"

        val body = """
            username=$username&toUserId=$userId&content=$content
        """.trimIndent()
        val header = HttpHeaders()
        header["Content-Type"] = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        request(path, HttpMethod.POST, HttpEntity(body, header))
    }


    @Test
    fun 发送系统消息并通知() {
        val path = "/api/imtools/system/msg/notification"
        val content = "系统消息测试——notification"
        val userId = ""
        val username = "kedong1"

        val body = """
            username=$username&toUserId=$userId&content=$content
        """.trimIndent()
        val header = HttpHeaders()
        header["Content-Type"] = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        request(path, HttpMethod.POST, HttpEntity(body, header))
    }

}

class IMTest : BaseTest() {

    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.DEV_THIRD
    }

    // 测试环境
//    val fromUserId = "4c92c477d018dd21b77b2f3acce1ce67"

    // 生产环境 【系统通知】id
    val fromUserId = "16e114eea972da14aecb00e1bd1ef9e7"

    @Test
    fun 文本消息() {
        val path = "/im/message/send/single"
        val body = HashMap<String, String>()
        body["content"] = "系统消息测试12"
        body["fromUserId"] = fromUserId// daxiong
        body["toUserId"] = ""
        body["toUserName"] = "kedong1"

        val header = HttpHeaders()
        header["Content-Type"] = "application/json"
        request(path, HttpMethod.POST, HttpEntity(JSON.toJSONString(body), header))
    }

    @Test
    fun 图文消息() {
        val path = "/im/message/send/imgtext"
        val json = """
            {
              "userIds": [],
              "userNames": [
                "kedong1"
              ],
              "articles": [
                {
                  "title": "123",
                  "digest": "摘要信息",
                  "url": "http://www.baidu.com",
                  "content": "ajlskdfjla",
                  "picurl": "http://asdfkasdf",
                  "push_content": "fasdf",
                  "push_data": "fasdf",
                  "persistent": 0,
                  "countable": 0
                }
              ]
            }
        """.trimIndent()
        val header = HttpHeaders()
        header["Content-Type"] = "application/json"
        request(path, HttpMethod.POST, HttpEntity(json, header))
    }


    @Test
    fun 二维码验证() {
        val path = "/im/message/qrcode/verify"
        val username = "yangyezhou"
        val qrcode = "0A1GYZfKTqYjIN4EXqg5Mc"
        val body = "qrcode=$qrcode&username=$username"


        val header = HttpHeaders()
        header["Content-Type"] = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        request(path, HttpMethod.POST, HttpEntity(body, header))
    }

    @Test
    fun 二维码确认() {
        val path = "/im/message/qrcode/confirm"
        val username = "yangyezhou"
        val qrcode = "0A1GYZfKTqYjIN4EXqg5Mc"
        val body = "qrcode=$qrcode&username=$username"


        val header = HttpHeaders()
        header["Content-Type"] = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        request(path, HttpMethod.POST, HttpEntity(body, header))
    }


    /**
     * 我换成高听的账号了 ： chengaoting		gssl@111111
    群ID：zQ22KKZTQakqd09pbY3KG8
    群名称：测试
     */
    @Test
    fun 登录() {
        val path = "/im/message/rce/login"
//        val username = "caoxun"
//        val password = AES.PKCS5Padding.encrypt("caoxun@123456", rongLoginKey)
        var username = "liudandan_s"
//        val password = "gssl@123456"
        var password = "uh@@1#n\$%^"

//        username = "superadmin"
//        password = "superadmin"

//        username = "chengaoting"
//        password = "gssl@111111"

        username = "kedong1"
        password = "kedong@123"

        val header = HttpHeaders()
        header["Content-Type"] = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        val response = request(
            path, HttpMethod.POST, HttpEntity(
                "username=$username&passw=${
                    URLEncoder.encode(
                        AES.PKCS5Padding.encrypt(password, rongLoginKey)
                    )
                }", header
            )
        )
        val body = response.body
        val json = JSON.parseObject(body)
        val rce = json.getJSONObject("result")
        val cookie = rce.getString("Cookie")
        val token = rce.getString("token")

        println(
            """
            
            
        """.trimIndent()
        )
        println(
            """
                header["Cookie"] = "$cookie"
                header["rce-token"] = "$token"
        """.trimIndent()
        )
    }
}

class RCE : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL
        return ServerApi.PROD_THIRD
    }

    @AfterEach
    override fun after() {
        if (response == null) return
        println("============================\r\n")
        val body = (response.body)
        val json: JSONObject = JSONObject.parseObject(body)
        val code = json.getIntValue("code")
        assert(response.statusCodeValue == 200)
        assert(code == 10000)

        println(
            JSON.toJSONString(
                json,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat
            )
        )

    }

    override fun getToken(): String {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NDE3LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiZTc4OTlmMTNmZDZkNGUwMWFmNzU4MTg2ZGFlN2IxNDUiLCJpYXQiOjE2NDI2ODk2OTMsInVzZXJuYW1lIjoia2Vkb25nMSJ9.-k7764ie_uFhStvJgDAromfWVb5FVBWv2fmJYjF7-ew"
    }

    @Test
    fun 查群() {
        val header = commonHeader()
        val path = "/rce/groups/mine?type=custom"
        request(path, HttpMethod.GET, HttpEntity(header))

    }

    @Test
    fun 建群() {
        val count = 1
        for (i in 1..count) {
            val header = commonHeader()
            header["Content-Type"] = "application/json"

            val path = "/rce/groups"
            val json = JSONObject()
            json["type"] = 0
            json["name"] = "建群测试000" + i
            val member_ids = ArrayList<String>()
            member_ids.add("7a2f23dc23e84099f6e343af69b9285d")// 杨叶舟
            member_ids.add("b430f1854a59d04fa9460225103c25c5")// 刘丹丹
            member_ids.add("daa57e76488ce6f44c225ad33e6b4c1d")
//            member_ids.add("4c92c477d018dd21b77b2f3acce1ce67")
            json["member_ids"] = member_ids
            json["manager_id"] = "7a2f23dc23e84099f6e343af69b9285d"
            var body = json.toJSONString()
            //language=JSON
            body = """
                {
                  "type": 0,
                  "name": "冯胜,刘丹丹,大雄,陈淑敏",
                  "member_ids": [
                    "49e6a011201eab3428081053668b95ac",
                    "b430f1854a59d04fa9460225103c25c5",
                    "4c92c477d018dd21b77b2f3acce1ce67",
                    "35b08c97c5918d4ac5bb6dcd4209a80a"
                  ],
                  "manager_id": "49e6a011201eab3428081053668b95ac"
                }
            """.trimIndent()
            request(path, HttpMethod.POST, HttpEntity(body, header))
        }
    }

    @Test
    fun 获取群基本信息() {
        """
            http://60.13.54.71:8098/im/QR.html?key=rce://group/join?code& hO1Bf6JoSfosEYswkRbos0 & b430f1854a59d04fa9460225103c25c5
        """.trimIndent()
        var groupId = "Z2livUZrTgkrb1HsHy7FIA"
        val path = "/rce/groups/$groupId"
        request(path, HttpMethod.GET, HttpEntity(commonHeader()))
    }

    @Test
    fun 解散群() {
        val groupId = "4xpctLZxT8YlIfjvFNGGf8"
        val path = "/rce/groups/$groupId"
        request(path, HttpMethod.DELETE, HttpEntity(commonHeader()))
    }


    @Test
    fun 修改群名称() {
        val groupId = "alBOMmrsQOMjJy3ws-32KI"
        val path = "/rce/groups/$groupId/name"
        val body = JSONObject()
        body["name"] = "123测试"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.PUT, HttpEntity(body.toJSONString(), headers))
    }

    @Test
    fun 拉人进群() {
        val groupId = "6ao-I-djRiguWSBzTtzUSI"
        val path = "/rce/groups/$groupId/invite"

        val ids = ArrayList<String>()
        ids.add("1c7d661fa79d96ab1efb79cdee7b85b4")// kedong
        val body = JSONObject()
        body["ids"] = ids

        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.POST, HttpEntity(body.toJSONString(), headers))
    }

    @Test
    fun 退群() {
        val groupId = "Byjz0Be9QA4tZBLgcxH4xg"
        val path = "/rce/groups/$groupId/quit"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.POST, HttpEntity(headers))
    }

    @Test
    fun 获取群成员() {
        val groupId = "kAfiML8GRMMqWI4rebcHV4"
        val path = "/rce/groups/$groupId/members?from=1&size=20"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.GET, HttpEntity(headers))
    }

    @Test
    fun getUserInfo() {
        val userId = "b430f1854a59d04fa9460225103c25c5"
        val path = "/rce/user/$userId"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.GET, HttpEntity(headers))
    }

    @Test
    fun 配置() {
        val path = "/rce/configuration/all"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.GET, HttpEntity(headers))
    }


    /**
     * http://60.13.54.71:8098/im/QR.html?key=rce://group/join?code&01fHtK5CQxUnlUi1su_eww&b430f1854a59d04fa9460225103c25c5
     */

    @Test
    fun 扫码进群() {
        val gid = "FttFyPt_SDojt0I5_wiG8w"
        val path = "/rce/groups/$gid/join"
        val body = """
            {
            "join_info": "{\"type\":0,\"operatorId\":\"274fb93d627a0c9146c94385f71e0f6e\"}"
            }
        """.trimIndent()
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        post(path, body, headers)
    }

    @Test
    fun 获取自己的昵称() {
        val path = "/rce/user/alias"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.GET, HttpEntity(headers))
    }

    @Test
    fun 获取所有部门树() {
        val path = "/rce/departments/tree"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.GET, HttpEntity(headers))
    }


    @Test
    fun 发群公告() {
        val gid = "l-IRBRz7Rc0usK4zA1NVfI"
        val path = "/rce/groups/${gid}/notice/publish"
        @Language("JSON") val body = """
            {
            "title": "这是群公告的标题",
            "content": "这是群公告的内容"
            }
        """.trimIndent()
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.POST, HttpEntity(body, headers))
    }

    @Test
    fun 获取群公告() {
        val gid = "l-IRBRz7Rc0usK4zA1NVfI"
        val path = "/rce/groups/${gid}/notice"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.GET, HttpEntity(headers))
    }

    @Test
    fun 修改群成员备注名() {
        val gid = "zQ22KKZTQakqd09pbY3KG8"
        val userId = "713f71e0b6e5b4d646de8240d7accb4e"
        val path = "/rce/groups/${gid}/members/${userId}/alias"
        @Language("JSON") val body = """
            {
            "alias": "昵称测试"
            }
        """.trimIndent()
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.PUT, HttpEntity(body, headers))
    }

    @Test
    fun 清除群成员昵称() {
        val gid = "zQ22KKZTQakqd09pbY3KG8"
        val userId = "713f71e0b6e5b4d646de8240d7accb4e"
        val path = "/rce/groups/${gid}/members/${userId}/alias"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        request(path, HttpMethod.DELETE, HttpEntity(headers))
    }

    @Test
    fun 获取会话配置信息() {
        val path = "/rce/conversation?version="
        get(path, commonHeader())
    }

    @Test
    fun 消息免打扰() {
        val path = "/rce/conversation/notdisturb"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        val body = """
            {
            "conversation_type":3,
            "target_id":"NWKQ6Ta8RDEg7vA4GElTvw",
            "not_disturb":1
            }
        """.trimIndent()
        request(path, HttpMethod.PUT, HttpEntity(body, headers))
    }


    @Test
    fun 创建二维码() {
        val path = "/rce/conversation/notdisturb"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        val body = """
            {
            "conversation_type":3,
            "target_id":"NWKQ6Ta8RDEg7vA4GElTvw",
            "not_disturb":1
            }
        """.trimIndent()
        request(path, HttpMethod.PUT, HttpEntity(body, headers))
    }


    @Test
    fun 新增用户() {
        @Language("JSON")
        val body = """
            {
            "keywords": ["15693102669"]
            }
        """.trimIndent()
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        val path = "/rce/staffs/search/mobile"
        request(path, HttpMethod.POST, HttpEntity(body, headers))
    }

    @Test
    fun 用户查询() {
        @Language("JSON")
        val body = """
            {
            "keywords": ["15693102669"]
            }
        """.trimIndent()
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        val path = "/rce/staffs/search/mobile"
        request(path, HttpMethod.POST, HttpEntity(body, headers))
    }

    @Test
    fun 用户查询姓名() {
        @Language("JSON")
        val body = """
            {
            "keywords": ["王云","刘凡","闫开卫"]
            }
        """.trimIndent()
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        val path = "/rce/staffs/search"
        request(path, HttpMethod.POST, HttpEntity(body, headers))
    }
}

class Portal : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.PROD_PORTAL
//        return ServerApi.LOCAL_PORTAL
        return ServerApi.PRE_PROD_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI0MmM0NzkxNTUwYTk0NjQ5OTUxNzUzNmJmYjI1MGVhOCIsInJhbmRvbSI6OTQ5LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiNzEzZjcxZTBiNmU1YjRkNjQ2ZGU4MjQwZDdhY2NiNGUiLCJ1dUlkIjoiZWVjZDQwNTA4OTJkNGM5MmFiMjM3OGFiNTQ3ZDYxZjUiLCJpYXQiOjE2NjE4NDUzMDAsInVzZXJuYW1lIjoiY2hlbmdhb3RpbmcifQ.PE-1ERLVUTIWDFCOQ8MfixVn3GLnOYYI-hqUc1vKpfY
        """.trimIndent()
    }

    @Test
    fun 群聊推送() {
        val path = "/api/chat/group/push"
        val headers = commonHeader()
        headers["Content-Type"] = "application/x-www-form-urlencoded"

        val groupId = "uO6BeBfATOEuEuzgxHTn-4"
        val fromId = "7a2f23dc23e84099f6e343af69b9285d"
        val title = URLEncoder.encode("群聊消息推送测试123")
        val content = URLEncoder.encode("群聊消息推送测试")
        val payload = """
            {"fromId":"4c92c477d018dd21b77b2f3acce1ce67","groupId":"CRj3JDJnRmYlqiDOLuWwAI","type":"100001","conversationType":"3","msgType":"1"}
        """.trimIndent()

        post(path, "groupId=$groupId&fromId=$fromId&title=$title&content=$content&payload=$payload", headers)
    }

    @Test
    fun 单聊推送() {
        val path = "/api/index/im/push/"
        val headers = commonHeader()
        headers["Content-Type"] = "application/x-www-form-urlencoded"

        val userId = "35b08c97c5918d4ac5bb6dcd4209a80a"
        val title = URLEncoder.encode("陈高听")
        val content = URLEncoder.encode("免打扰推送测试")
        val payload = """
            {"conversationType":"1","msgType":"1","groupId":"4c92c477d018dd21b77b2f3acce1ce67","type":"100001","fromId":"713f71e0b6e5b4d646de8240d7accb4e"}
        """.trimIndent()

        post(path, "userId=$userId&title=$title&content=$content&payload=$payload", headers)
    }


    @Test
    fun groupName() {
        val path = "/api/chat/group/names"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"

        val body = ArrayList<String>()
        body.add("Hy3sVRVKQeYjxth83lYDic")
        request(path, HttpMethod.POST, HttpEntity(JSONObject.toJSONString(body), headers))
    }

    @Test
    fun quit() {
        val path = "/api/chat/group/quit?groupId=Byjz0Be9QA4tZBLgcxH4xg"
        val headers = commonHeader()

        request(path, HttpMethod.POST, HttpEntity(headers))
    }


    @Test
    fun 扫码进群() {
        val gid = "01fHtK5CQxUnlUi1su_eww"
        val userId = "b430f1854a59d04fa9460225103c25c5"
        val path = "/api/chat/group/$gid/join?userId=$userId"
        val headers = commonHeader()

        request(path, HttpMethod.POST, HttpEntity(headers))
    }


    @Test
    fun rcx注册() {
        val path = "/api/im/user/getToken.json"
        val username = "slhsfy"
        val userId = "0467fd0c25f5b1d9e0fddb455c0fbb46"

        val headers = HttpHeaders()
        headers["Content-Type"] = "application/x-www-form-urlencoded"
        request(path, HttpMethod.POST, HttpEntity("userId=$userId&name=$username", headers))
    }


    @Test
    fun 获取部门id() {
        val deptId = "31e27fc160f54bee8e9d6a3401729164"
        val path = "/api/user/getDeptId?deptId=$deptId"
        val headers = commonHeader()
        request(path, HttpMethod.GET, HttpEntity(headers))
    }

    @Test
    fun 同步用户到融云() {
        val username = "yangle"
        val path = "/api/user/sync/to/rce?username=$username"
        val headers = commonHeader()
        request(path, HttpMethod.GET, HttpEntity(headers))
    }


    @Test
    fun 消息免打扰() {
        val path = "/api/imtools/conversation/notdisturb"
        val headers = commonHeader()
        headers["Content-Type"] = "application/json"
        val body = """
            {
            "userId": "abc",
            "conversationType":3,
            "targetId":"NWKQ6Ta8RDEg7vA4GElTvw",
            "notDisturb":1
            }
        """.trimIndent()
        request(path, HttpMethod.PUT, HttpEntity(body, headers))
    }

    @Test
    fun 获取消息免打扰设置() {
        val userId = "abc"
        val targetId = "NWKQ6Ta8RDEg7vA4GElTvw"
        val path = "/api/imtools/conversation/notdisturb?userId=$userId&targetId=$targetId"
        get(path)
    }
}

class 发消息 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_PORTAL
    }

    @Test
    fun 发送群消息() {
        val path = "/api/im/message/group/publish.json"
        // 大雄
        val fromUserId = "4c92c477d018dd21b77b2f3acce1ce67"
        // 重复推送测试 rptHS09tSVUqsUL0QFBudw
        val toGroupId = "rptHS09tSVUqsUL0QFBudw"
        val objectName = "RC:TxtMsg"
        val content = """
            {"content":"hello","extra":"helloExtra"}
        """.trimIndent()


        val body = """
            content=$content&fromUserId=$fromUserId&toGroupId=$toGroupId&objectName=$objectName
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_FORM_URLENCODED)
    }

}

class Uniauth : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_UN
//        return ServerApi.PRE_PROD_UN
        return ServerApi.PROD_UN
//        return ServerApi.DEV_UN
    }

    @Override
    override fun after() {
    }

    @Test
    fun smsLogin() {
        val path = "/uniauth/auth/app/smsLogin"
        val requestBody = """
             {"userName": "13277918809", "exMap": {"code": "560565"}}
        """.trimIndent()
        // 获取验证码  http://60.13.54.71:30119/yunli2/third/v1/cache/value?key=sms:13277918809
        val header = HttpHeaders()
        header["Content-Type"] = MediaType.APPLICATION_JSON_VALUE
        val response = request(path, HttpMethod.POST, HttpEntity(requestBody, header))
        val body = response.body
        val json = JSON.parseObject(body)
        val result = json.getJSONObject("result")
        val rce = result.getJSONObject("rce")
        val cookie = rce.getString("Cookie")
        val token = rce.getString("token")

        println(
            """
            
            
        """.trimIndent()
        )
        println(
            """
                header["Cookie"] = "$cookie"
                header["rce-token"] = "$token"
        """.trimIndent()
        )
    }

    @Test
    fun app登录() {
        val path = "/uniauth/auth/app/login"
//        val username = "lixiaoning"
//        val password = "Lxn_Xq07229318"
//        lixiaoning / Lxn_Xq07229318
//        tiandayong/Tiandy@135
//        val username = "tiandayong"
//        val password = "Tiandy@135"   xuziling / hzz@Yunli1234
        var username = "yangyezhou"
        var password = "gssl@12345678"
        username = "kedong1" // 94f3962a638966037ffa9f7d7c26cdc3
        password = "kedong@123"
        val cid = "4b4a6a7dc477598b40c3546bd683eba4"
//        val cid = "1111111111111111111111111"

        val header = HttpHeaders()
        header["Content-Type"] = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        val response = request(
            path, HttpMethod.POST, HttpEntity(
                "cid=$cid&username=$username&passw=${
                    URLEncoder.encode(
                        AES.PKCS5Padding.encrypt(password, rongLoginKey)
                    )
                }", header
            )
        )
        val body = response.body
        println(body)
        val json = JSON.parseObject(body)
        val result = json.getJSONObject("result")
        val rce = result.getJSONObject("rce")
        val cookie = rce.getString("Cookie")
        val token = rce.getString("token")

        println(
            """
            
            
        """.trimIndent()
        )
        println(
            """
                header["Cookie"] = "$cookie"
                header["rce-token"] = "$token"
        """.trimIndent()
        )
    }

}

fun commonHeader(): HttpHeaders {
    val header = HttpHeaders()

    header["Cookie"] = "RCESESSIONID=GuFS5yT9RpQvHk1B_O8Iik; Path=/api; Max-Age=2592000; Expires=Fri, 22-Sep-2023 03:16:16 GMT; HttpOnly"
    header["rce-token"] = "MaencFXL2569CLEHbqXjc/D6jHsCjm1oH+I7BcIZfmKC9XEqy8zpmFhsr+aNsAZCFTvfOuDcxxnZ338X+tM9MTzjP8O63f/FVLR4KQ=="

    return header
}
