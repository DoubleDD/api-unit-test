package com.example.springboot.http.甘肃.应用中台升级.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class AddressListController : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_PORTAL
//        return ServerApi.DEV_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6OTM3LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiYmVjNTY5NGZmOWIzNDU0NzhmYTJlNjM5YTdlMDdhZjkiLCJpYXQiOjE2NTQ3NDU0ODUsInVzZXJuYW1lIjoia2Vkb25nMSJ9.eui9PlzCILVMs7qScqjjJxlAkwm5GpFxgZug6DxzgAo
        """.trimIndent()
    }

    @Test
    fun 新建通讯录() {
        val path = "/api/addressList/add"
        val body = """
            
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 通讯录列表() {
        val path = "/api/addressList/auth/v2/pageList"
        val body = """
            {"deptId":"20220107164610929053312337317888","realname":"","global":false,"pageSize":15,"pageNum":1}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 通讯录详情() {
        val id = "20220107164639929053431556214784"
        val path = "/api/addressList/detail?id=$id"
        get(path)
    }

    @Test
    fun 更新() {
        val body = """
            {"deptId":"20220107164610929053312337317888","deptName":"省办公厅-市办公厅","realname":"防汛值班","jobTitle":null,"tel":"17512011990","gender":2,"fixedLine":"027-77777","officeAddress":null,"officeNo":null,"floodEmergencyTel":"027-77777","governmentTel":"027-77777","governmentSecretaryTel":"027-77777","fax":"027-77778","remark":null,"email":null,"id":"20220609165337984500528744763392"}
        """.trimIndent()
        val path = "/api/addressList/update"
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 导入() {
        val path = "/api/addressList/importData"
        postFile(path, "/home/kedong/Downloads/通讯录导入模板.xlsx")
    }

}