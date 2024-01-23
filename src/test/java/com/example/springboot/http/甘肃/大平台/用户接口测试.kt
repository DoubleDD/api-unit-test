package com.example.springboot.http.甘肃.大平台

import com.alibaba.fastjson.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.io.File

class 用户接口测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_PORTAL
//        return ServerApi.DEV_PORTAL
        return ServerApi.PRE_PROD_PORTAL
//        return ServerApi.PROD_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NzMwLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiODMyYTFjYTcwZWQwNDJjNTg2NDk2ZjE3OWE1YmFmNGQiLCJpYXQiOjE2ODg2MTI1MDAsInVzZXJuYW1lIjoia2Vkb25nMSJ9.AH77xrVT1-ApXgsRlEOo94gQfqLpPskrQO4rYLrPY0A
        """.trimIndent()
    }

    @Test
    fun 查询用户所有角色权限关系图() {
        val username = "kedong1"
        val path = "/api/user/relation/graph/$username"
        val header = HashMap<String, String>()
//        header["Cookie"] = "token=abc"
        val res = get(path, header)
        val file = File(System.getProperty("user.home") + "/Downloads/${username}-角色权限.json")
        res.body?.let { file.writeText(it) }
    }

    @Test
    fun 用户导入() {
        val fileName = "/home/kedong/Download/用户导入模板.xlsx"
        val path = "/api/user/importData"
        val paramMap: MultiValueMap<String, Any> = LinkedMultiValueMap()
        val resource: Resource = FileSystemResource(File(fileName))

        val list = ArrayList<Any>()
        list.add(resource)

        paramMap.put("file", list)

        post(path, paramMap, MediaType.MULTIPART_FORM_DATA)
    }

    val userList = """
        {"code":0,"message":"SUCCESS","result":{"pageNum":1,"pageSize":10,"startRow":1,"endRow":10,"total":35,"pages":4,"data":[{"authorityUserList":null,"authorityTypeList":null,"id":"732c1662f215d8c8a5b8e9b3f743badf","userName":"baiyinyin","realname":"白银银","email":null,"gender":2,"tel":"13830611590","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局/水利勘测设计队","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"0654f16d8aa6a9b738eea3fcf06cd295","jobTitle":null,"deptName":"张掖市-山丹县水务局-水利勘测设计队","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"1fe59e1f60f34fd47ee9fabf86ef48aa","userName":"chenjie_sd","realname":"陈洁","email":null,"gender":2,"tel":"15025886964","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局/抗旱防汛服务队","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"8780f07c9d7cc83d6973b70aa528e1c5","jobTitle":null,"deptName":"张掖市-山丹县水务局-抗旱防汛服务队","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"e032ba398c769932077ff683291b4a18","userName":"gaojingwen","realname":"杲婧雯","email":null,"gender":2,"tel":"18793663447","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局/老军河水利管理所","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"7ead487ccc168bdbc72281e0e060f365","jobTitle":null,"deptName":"张掖市-山丹县水务局-老军河水利管理所","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"2c27f4929e494e7a413c3c90cf62ee0b","userName":"gongshibo","realname":"龚世博","email":null,"gender":1,"tel":"13830696621","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"82cb8d7162cc4325a714aa8b6b48ab3e","jobTitle":null,"deptName":"张掖市-山丹县水务局","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"f0822532ee2bb46e845f13b3b6ba04b8","userName":"gongxiuhong","realname":"龚秀红","email":null,"gender":2,"tel":"15103907713","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局/霍城河水利管理处","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"f2621f4c9bd5194cfcb3e5bb19c54400","jobTitle":null,"deptName":"张掖市-山丹县水务局-霍城河水利管理处","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"2638b56c2b92b4208572b9d6ef06175a","userName":"guohuiying","realname":"郭慧英","email":null,"gender":1,"tel":"13830697551","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局/水资源保护服务中心","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"9d14809dc1f83bca5a3426af84ceb286","jobTitle":null,"deptName":"张掖市-山丹县水务局-水资源保护服务中心","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"82ac80814463433963df281dbbd75320","userName":"hechunzhen","realname":"何纯珍","email":null,"gender":1,"tel":"13830666540","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"82cb8d7162cc4325a714aa8b6b48ab3e","jobTitle":null,"deptName":"张掖市-山丹县水务局","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"d008498898c8de88dc2592cabbd251f9","userName":"leiliping","realname":"雷丽萍","email":null,"gender":2,"tel":"19993642584","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局/水政监察大队","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"248b894b252421a14770305e7acbd197","jobTitle":null,"deptName":"张掖市-山丹县水务局-水政监察大队","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"6f8a192df50460114d1352afb0884387","userName":"ligang","realname":"李刚","email":null,"gender":1,"tel":"13649366412","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局/马营河流域管理处","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"d423d52867962afcebe9cf3c80d3e1ce","jobTitle":null,"deptName":"张掖市-山丹县水务局-马营河流域管理处","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"},{"authorityUserList":null,"authorityTypeList":null,"id":"52af6071be2f8f44e60c87f72578771c","userName":"liuboxiong","realname":"刘伯雄","email":null,"gender":1,"tel":"13519366640","fixedLine":null,"regionTreeCode":null,"regionTreeName":"甘肃省/市（州）、县（区）水务（水保）局/张掖市/山丹县水务局","regionId":1412134,"regionName":null,"sortNum":null,"imageUrl":null,"deptId":"82cb8d7162cc4325a714aa8b6b48ab3e","jobTitle":null,"deptName":"张掖市-山丹县水务局","deptTreeName":null,"roleCode":null,"roleId":null,"roleIds":null,"departments":null,"officeAddress":null,"officeNo":null,"remark":null,"imFlag":null,"smsFlag":null,"addressBookFlag":null,"scheduleFlag":null,"scheduleShow":null,"managementScopeType":"null","managementScopeName":null,"managementScope":null,"addressManagementScopeName":null,"addressManagementScope":null,"normalAdminFlag":"0","isExistAddress":null,"createTime":null,"ustatus":"1"}]}}
    """.trimIndent()

    @Test
    fun 批量启用用户状态() {
        var json = JSON.parseObject(userList)
        var result = json.getJSONObject("result")
        var data = result.getJSONArray("data")
        for (item in data) {
            println(item)
        }
    }


    @Test
    fun 给所有用户绑定角色() {
        val roleId = "aaf1136b9d3d4f0e9b7a438f8b0e5ad9"
        val path = "/api/role/setRoletoAllUser"
        post(path, "roleId=$roleId", MediaType.APPLICATION_FORM_URLENCODED)
    }


    @Test
    fun 查询是否为新设备() {
        val username = "kedong1"
        val deviceId = "abc"
        val path = "/api/user/query/device?username=$username&deviceUuid=$deviceId"
        get(path)
    }

    @Test
    fun 绑定新设备() {
        @Language("JSON")
        val body = """
            {
              "userId": "94f3962a638966037ffa9f7d7c26cdc3",
              "deviceUuid": "abc",
              "deviceName": "huawei",
              "deviceOs": "鸿蒙",
              "deviceOsVersion": "2.0"
            }
        """.trimIndent()
        val path = "/api/user/bind/device"
        post(path, body, MediaType.APPLICATION_JSON)
    }


    @Test
    fun 同步用户到融云() {
        val username = "yankaiwei"
        val path = "/api/user/sync/to/rce"
        get("$path?username=$username")
    }
}

class Third : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
    }


    @Test
    fun 修改新用户中心密码() {
        val userId = "713f71e0b6e5b4d646de8240d7accb4e"// chengaoting
        val password = "gssl@123456"
        val path = "/api/data/center/syncPwd"
        post(path, "userId=$userId&password=$password", MediaType.APPLICATION_FORM_URLENCODED)
    }

}