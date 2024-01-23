package com.example.springboot.http.甘肃.水利年鉴

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON

class 文稿收发 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_YEARBOOK
//        return ServerApi.PROD_YEARBOOK
//        return ServerApi.DEV_YEARBOOK
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MjU4LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNGI4ZjMyNzIxYTM4NDhkMDhiMzE2NzliMTJhMjI5NDkiLCJpYXQiOjE2NjMwNTM0MTcsInVzZXJuYW1lIjoia2Vkb25nMSJ9.8vnHZZvjvBU7tDN--x0lM9FPZsaMLhdnA31G1C7yU0I
        """.trimIndent()
    }

    @Test
    fun 文稿列表() {
        val body = """
{"userId":"f0b0ecba84b1816c0f7d80fd0ef0a0a2","deptId":"2661c765337e4b55881dff633b641096","currentIdentity":2,"manuscriptsId":"1549706896743538690","missionPhase":2,"searchDeptIds":"921c1edfbd634f23b5d6051cd0bb1595"}
        """.trimIndent()
        val path = "/manuscripts/V2/listById"
        post(path, body, APPLICATION_JSON)
    }

    @Test
    fun 年鉴详情() {
        val id = "20220721173654999731714165313536"
        get("/manuscripts/getManuscriptsExamineOne?id=$id")
    }

    @Test
    fun 年鉴编写() {
        val path = "/manuscripts/uploadList"
//        {"id":"1550055785314840578","createTime":"2022-07-21 17:46:29","fileName":"2022-07-11_16-33.png","fileSize":"303.19KB","fileType":"image/png","fileUrl":"http://39.100.95.69:30101/oss2/announcement/14273317002022-07-11_16-33.png","manuscriptsExamineId":"20220721173654999731714165313536","manuscriptsId":"1550041788289052674","originaName":"14273317002022-07-11_16-33.png","missionPhase":0,"status":1,"status2":0}
        @Language("JSON") val args = """
            {
              "content": {
                "id": "1550055785323229185",
                "number": "456"
              },
              "files": [
                {
                  "id": "1550057169716547585",
                  "createTime": "2022-07-21 17:46:39",
                  "fileName": "morepanel2_29.jpg",
                  "fileSize": "19.11KB",
                  "fileType": "image/jpeg",
                  "fileUrl": "http://39.100.95.69:30101/oss2/announcement/166660418morepanel2_29.jpg",
                  "manuscriptsExamineId": "20220721173654999731714165313536",
                  "manuscriptsId": "1550041788289052674",
                  "originaName": "166660418morepanel2_29.jpg",
                  "missionPhase": 0,
                  "status": 1,
                  "status2": 0
                }
              ],
              "id": "20220721173654999731714165313536",
              "editName": "柯栋"
            }
        """.trimIndent()

        post(path, args, APPLICATION_JSON)

    }

    @Test
    fun 获取通知列表() {
        val path = "/manuscripts/getMessage?deptId=3e104403fd874510a4899ac2a5206f5e"
        get(path)
    }

    @Test
    fun 下拉列表() {
        val path = "/manuscripts/selectList"
        get(path)
    }


    @Test
    fun 任务详情() {
        val id = "1546377480739041281"
        get("/manuscripts/taskDetails?id=$id")
    }


    @Test
    fun 流程() {
        val path =
            "/manuscripts/getFlow?userId=9f6620875b680614584725f74fbcc66a&deptId=813ca83ffeba47419c0165d71d18b7c0&manuscriptsExamineId=20220304161811949339989760806912"
        get(path)
    }


    @Test
    fun 批量审核() {
        val path = "/manuscripts/updateBatch"
        val body = arrayOf("20220220034802944802555115081729", "20220220035802944805070846038017")
        post(path, JSON.toJSONString(body), APPLICATION_JSON)
    }


    @Test
    fun 批量下载() {
        val path = "/manuscripts/zipDowloadIdV2"
        post(
            path, """
            {"ids":["202208112254031007421671436718080","202208112254031007421671436718081"]}
        """.trimIndent(), APPLICATION_JSON
        )
    }


    @Test
    fun 催办() {
        val path = "/todo/todoManuscripts/1508115066855092226"
        val body = """
            [{"id":"20220328001346957794593360056331"}]
        """.trimIndent()
        post(path, body, APPLICATION_JSON)
    }

    @Test
    fun 待办数量() {
        val path = "/todo/todoManuscripts/1508115066855092226"
        val body = """
            [{"id":"20220328001346957794593360056331"}]
        """.trimIndent()
        post(path, body, APPLICATION_JSON)
    }

    @Test
    fun 撤回() {
        val path = "/manuscripts/withdraw"
        val body = """
            {"editUserName":"柯栋","examineId":"20220718145425998603660349149184","status":"3","postscript":""}
        """.trimIndent()
        post(path, body, APPLICATION_JSON)
    }

    @Test
    fun 保存修改年鉴() {
        val path = "/manuscripts/save"
        var body = """
            {"manuscripts":{"id":"1548916440593195009","manuscriptsName":"测试-20220718-3","missionPhase":1,"invalidTime":"2022-10-06 14:23:35","dept":[{"deptName":"财务处","deptId":"cc3743f640cb4bb3b148790c485f9d12"},{"deptName":"人事处","deptId":"921c1edfbd634f23b5d6051cd0bb1595"},{"deptName":"水库移民处","deptId":"f05735bbbfc04ce0ad1dd0748ab4fcff"}],"groupDraftDept":[{"deptId":"cc3743f640cb4bb3b148790c485f9d12","deptName":"财务处","regionId":null,"remark":null,"deptType":null,"id":"cc3743f640cb4bb3b148790c485f9d12","sortNum":null,"level":null,"deptDataType":null,"pid":null},{"deptId":"921c1edfbd634f23b5d6051cd0bb1595","deptName":"人事处","regionId":null,"remark":null,"deptType":null,"id":"921c1edfbd634f23b5d6051cd0bb1595","sortNum":null,"level":null,"deptDataType":null,"pid":null},{"deptId":"f05735bbbfc04ce0ad1dd0748ab4fcff","deptName":"水库移民处","regionId":null,"remark":null,"deptType":null,"id":"f05735bbbfc04ce0ad1dd0748ab4fcff","sortNum":null,"level":null,"deptDataType":null,"pid":null}],"firstProofreadDept":[],"secondProofreadDept":[],"deptId":"38fc2ef53acb4596b40c0d7c6f7b371c","createTime":"2022-07-18 14:23:51","createUser":"94f3962a638966037ffa9f7d7c26cdc3","userName":"柯栋","urgeStatus":"0","categoryName":null,"categoryId":null,"remarks":"","remarks2":"","remarks3":null,"isDelete":false},"files":[]}
        """.trimIndent()
        body = """
            {"manuscripts":{"id":"1549595556506333186","manuscriptsName":"测试-20220720-001年鉴","missionPhase":2,"invalidTime":"2022-07-28 11:22:22","dept":[{"deptName":"财务处","deptId":"cc3743f640cb4bb3b148790c485f9d12"},{"deptName":"人事处","deptId":"921c1edfbd634f23b5d6051cd0bb1595"},{"deptName":"外部单位","deptId":"d3bdf1b39674074a8bcb16dcc278b95f"}],"groupDraftDept":[{"deptId":"858371bc9dfcd086481fe7fc6e8cbd41","deptName":"省水文站","regionId":null,"remark":null,"deptType":null,"id":"858371bc9dfcd086481fe7fc6e8cbd41","sortNum":null,"level":null,"deptDataType":null,"pid":null},{"deptId":"cc3743f640cb4bb3b148790c485f9d12","deptName":"财务处","regionId":null,"remark":null,"deptType":null,"id":"cc3743f640cb4bb3b148790c485f9d12","sortNum":null,"level":null,"deptDataType":null,"pid":null},{"deptId":"921c1edfbd634f23b5d6051cd0bb1595","deptName":"人事处","regionId":null,"remark":null,"deptType":null,"id":"921c1edfbd634f23b5d6051cd0bb1595","sortNum":null,"level":null,"deptDataType":null,"pid":null},{"deptId":"e96633f4835c4868be0114c40fd70806","deptName":"水利工会","regionId":null,"remark":null,"deptType":null,"id":"e96633f4835c4868be0114c40fd70806","sortNum":null,"level":null,"deptDataType":null,"pid":null}],"firstProofreadDept":[{"deptId":"cc3743f640cb4bb3b148790c485f9d12","deptName":"财务处","regionId":null,"remark":null,"deptType":null,"id":"cc3743f640cb4bb3b148790c485f9d12","sortNum":null,"level":null,"deptDataType":null,"pid":null},{"deptId":"921c1edfbd634f23b5d6051cd0bb1595","deptName":"人事处","regionId":null,"remark":null,"deptType":null,"id":"921c1edfbd634f23b5d6051cd0bb1595","sortNum":null,"level":null,"deptDataType":null,"pid":null}],"secondProofreadDept":[{"deptId":"cc3743f640cb4bb3b148790c485f9d12","deptName":"财务处","regionId":null,"remark":null,"deptType":null,"id":"cc3743f640cb4bb3b148790c485f9d12","sortNum":null,"level":null,"deptDataType":null,"pid":null},{"deptId":"921c1edfbd634f23b5d6051cd0bb1595","deptName":"人事处","regionId":null,"remark":null,"deptType":null,"id":"921c1edfbd634f23b5d6051cd0bb1595","sortNum":null,"level":null,"deptDataType":null,"pid":null},{"deptId":"d3bdf1b39674074a8bcb16dcc278b95f","deptName":"外部单位","regionId":null,"remark":null,"deptType":null,"id":"d3bdf1b39674074a8bcb16dcc278b95f","sortNum":null,"level":null,"deptDataType":null,"pid":null}],"deptId":"38fc2ef53acb4596b40c0d7c6f7b371c","createTime":"2022-07-20 11:22:25","createUser":"94f3962a638966037ffa9f7d7c26cdc3","userName":"柯栋","urgeStatus":"0","categoryName":null,"categoryId":null,"remarks":"","remarks2":"年鉴管理第一次校稿阶段","remarks3":"年鉴管理第一次校稿阶段","isDelete":false},"files":[{"id":"1549778429362761730","fileUrl":"http://39.100.95.69:30101/oss2/announcement/314975001胡歌png.png","fileName":"胡歌png.png","fileSize":"304597","fileType":"image/png","missionPhase":2},{"id":"1549778429366956034","fileUrl":"http://39.100.95.69:30101/oss2/announcement/5432625635.5Mjpeg.jpg","fileName":"5.5Mjpeg.jpg","fileSize":"5823920","fileType":"image/jpeg","missionPhase":2},{"id":"1549778429366956035","fileUrl":"http://39.100.95.69:30101/oss2/announcement/402152195测试方案.docx","fileName":"测试方案.docx","fileSize":"13154","fileType":"application/vnd.openxmlformats-officedocument.wordprocessingml.document","missionPhase":2}]}
        """.trimIndent()
        post(path, body, APPLICATION_JSON)
    }

    @Test
    fun 确认() {
        val path = "/manuscripts/confirm"
        val body = """
            {"examineId":"20220717233301998371780978806784","status":"10","postscript":"aaaaaaaaaaaaaaaaaaaa","editUserName":"柯栋"}
        """.trimIndent()
        post(path, body, APPLICATION_JSON)
    }

    @Test
    fun 组稿提交() {
        val path = "/manuscripts/submit"
        val body = """
            {"editUserName":"赵丹","postscript":"","manuscriptsExamineId":"20220718145425998603660349149184"}
        """.trimIndent()
        post(path, body, APPLICATION_JSON)
    }

    @Test
    fun 校核提交() {
        val path = "/manuscripts/examine"
        val body = """
            {"id":"20220720112132999274860289200131","files":[{"fileId":"1549595875478958082","status":4}],"status":"11","editUserName":"赵丹","postscript":""}
        """.trimIndent()
        post(path, body, APPLICATION_JSON)
    }

}

class 大平台接口 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.DEV_PORTAL
    }

    @Test
    fun 管理员判断() {
        val userId = "94f3962a638966037ffa9f7d7c26cdc3"
        val path = "/api/auth/checkAuthCode/v2?userId=$userId&authCode=slnj_admin"
        get(path)
    }

}