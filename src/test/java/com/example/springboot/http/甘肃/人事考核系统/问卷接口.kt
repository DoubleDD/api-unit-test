package com.example.springboot.http.甘肃.人事考核系统

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 问卷接口 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_AD
//        return ServerApi.PROD_AD
        return ServerApi.PRE_PROD_AD
//        return ServerApi.DEV_AD
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJiMjRkZjM0MmUyZDY0MjZmODRlODA0NjE5NmZkNTk0NyIsInJhbmRvbSI6NzQzLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiNGM5MmM0NzdkMDE4ZGQyMWI3N2IyZjNhY2NlMWNlNjciLCJ1dUlkIjoiYmJlZTk4MWViNzFkNDE5ZDg0NDU3OTU0Yzc5ZDFlOTgiLCJpYXQiOjE2NzM0MDc4NjIsInVzZXJuYW1lIjoiZGF4aW9uZyJ9.jlEvK1QWWDvH48da3Z4rzW7W5EDF6BNuiynL-1o0Bpc
        """.trimIndent()
    }

    @Test
    fun 发布问卷() {
        val path = "/api/formExam/publish"
        @Language("JSON") val body = """
            {"id":"202301052203181060679915592224768","type":"1","timeLength":"24","startTime":"","endTime":"","voteNumber":"0-1","vcodes":["055121","141421","424511","515122","055233","212521","440552","034445","321531","525221","352050","233430","002401","451200","520142"]}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 保存问卷() {
        val path = "/api/formExam/save"

        @Language("JSON") var body: String = """
            {
              "formName": "有图片的问卷2",
              "formExplain": "",
              "questionList": [
                {
                  "comp": null,
                  "type": "radio",
                  "questionTitle": "题目",
                  "questionType": 1,
                  "questionOrder": 0,
                  "isRequired": 1,
                  "optionList": [
                    {
                      "optionContent": "文本0",
                      "optionOrder": 0,
                      "optionId": 0,
                      "selected": "0",
                      "slideMax": null,
                      "slideMin": null,
                      "images": "http://39.100.95.69:30102/oss/assessment/third/1.png"
                    },
                    {
                      "optionContent": "文本1",
                      "optionOrder": 1,
                      "optionId": 1,
                      "selected": "0",
                      "slideMax": null,
                      "slideMin": null,
                      "images": "http://39.100.95.69:30102/oss/assessment/third/2.png"
                    }
                  ],
                  "unit": ""
                }
              ]
            }
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }


    @Test
    fun 问卷详情接口() {
        val header = HashMap<String, String>()
        header["User-Agent"] = "MicroMessenger"

        val id = "202303081240351083006350780534784"
        val path = "/api/formExam/getDetails?id=$id"
        get(path, header)
    }

    @Test
    fun 保存至模板() {
        val templateName = "aaaaaaaaaaaaa"
        val id = "202301071649211061325685316128768"
        val userId = "94f3962a638966037ffa9f7d7c26cdc3"
        var path = "/api/formExam/saveToTemplate?templateName=$templateName&id=$id&userId=$userId"
        path="/api/formExam/saveToTemplate?id=202301131611291063490480186331136&templateName=%E9%95%BF%E9%A1%B5%E9%9D%A2%E6%B5%8B%E8%AF%95&userId=4c92c477d018dd21b77b2f3acce1ce67&canReview=1"
        get(path)

    }


    @Test
    fun 保存模板() {
        val path = "/api/template/updateTemplate"
        @Language("JSON") var body = """
           {"formName":"尔瓦人","formExplain":"","questionList":[{"questionOrder":0,"questionId":"202301131436451063466642014736384","questionTitle":"必填1","questionType":2,"isRequired":0,"maxOption":2,"minOption":0,"slideMin":null,"slideMax":null,"unit":null,"valueType":null,"blankAnswer":null,"optionList":[{"optionId":"202301131436451063466642014736385","optionOrder":0,"optionContent":"文本0","optionScore":null,"questionId":"202301131436451063466642014736384","selected":"0","maxOption":null,"minOption":null,"slideMin":0,"slideMax":0,"slideAnswer":null,"unit":null,"images":null,"detailsIds":null},{"optionId":"202301131436451063466642014736386","optionOrder":1,"optionContent":"文本1","optionScore":null,"questionId":"202301131436451063466642014736384","selected":"0","maxOption":null,"minOption":null,"slideMin":0,"slideMax":0,"slideAnswer":null,"unit":null,"images":null,"detailsIds":null}],"optionDetailsList":null,"style":"{}","type":"checkbox","comp":null}],"id":"202301131436081063466486733213696","isAnonymous":"1","formType":"1","canReview":1,"deptId":"","deptName":""}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }


    @Test
    fun 模板详情接口() {
        val id = "20220615121001986603487267917824"
        val path = "/api/template/getTemplateDetails?id=$id"
        get(path)
    }

    @Test
    fun 获取答案接口() {
        val id = "202301061055571060874360732258304"
        val phone = ""
        val deviceId = "202301061055571060874360732258304"
        val vcode = "455220"
        var path = "/api/formExam/getAnswer?formId=$id&phone=$phone&deviceId=$deviceId&vcode=$vcode"

        path = "/api/formExam/getAnswer?formId=202301150937111064116026574376960&deviceId=4ef40b2d5f32fa389fb4387165715abc0cfd8&phone=&vcode=552510"
        get(path)
    }


    @Test
    fun 验证码下载() {
        val path = "/api/formExam/vcode/download"
        @Language("JSON") val body = """
            {
            "codes":["055121","141421","424511","515122","055233","212521","440552","034445","321531","525221","352050","233430","002401","451200","520142"],
            "filename": "12-12-12-3-考核-验证码",
            "dept": "测试1",
            "url": "http://bing.com"
            }
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 是否可以投票() {
        val p = "formId=202301071557021061312515969126400&uid=18153692532"
        get("/api/formExam/isCanVote?$p")
    }


    @Test
    fun 历史列表接口() {
        var path = "/api/formExam/list"
        @Language("JSON") val body = """
            {"pageNum":1,"pageSize":1}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 下载报告() {
        val formId = "202301061646411060962625019711488"
        val path = "/api/assessment/exportAssessmentDeatils?formId=$formId"
        get(path)
    }

    @Test
    fun 生成问卷() {
        val path = "/api/template/generateQuestionnaire?id=202301111114291062690962729603072"
        get(path)
    }
}