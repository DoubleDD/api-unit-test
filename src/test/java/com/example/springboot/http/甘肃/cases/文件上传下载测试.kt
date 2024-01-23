package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import java.net.URLEncoder

class 文件上传下载测试 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.DEV_THIRD
//        return ServerApi.LOCAL_THIRD
        return ServerApi.PROD_THIRD
    }

    @Test
    fun upload() {
        val path = "/api/file/upload"
        postFile(
            path,
            "/home/haha/Pictures/bg.jpg",
        )
    }

    @Test
    fun download() {
        val f = URLEncoder.encode("bg.jpg")
        val path = "/api/file/download/$f"
        val header = HttpHeaders()
        header["referer"] = "http://60.13.54.71:30118"
        get(path, header)
    }

    @Test
    fun downloadv2() {
        val token =
            "12eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6MzM0LCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiOGNiM2RjNWZlMzI4NDBiNzliMDRkMmU5MWY5MWNjNjQiLCJpYXQiOjE2NzI5MzAwMzUsInVzZXJuYW1lIjoia2Vkb25nMSJ9.7z3PTuT8mGWVXnQhPzsqP5UheTs58gQ5OiDVGhduF7M"
        val filename = URLEncoder.encode("bg.jpg")
        val path = "/api/file/download/v2/$filename?token=$token"
        val header = HttpHeaders()
        header["referer"] = "http://60.13.54.71:30118"
        get(path, header)
    }

    @Test
    fun 值班表() {
        val path = "/api/file/upload/v2/default"
        postFile(
            path,
            "/home/kedong/Documents/工作/值班表/8值班表.pdf"
        )
    }

    @Test
    fun 大平台数据导入模板() {
        val path = "/api/file/upload/v2/default"

        postFile(
            path,
//            "/home/kedong/Downloads/通讯录导入模板.xlsx",
            "/home/kedong/Downloads/用户导入模板.xlsx",
        )
    }

}


class 人事考核文件上传 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PROD_AD
//        return ServerApi.LOCAL_AD
    }

    @Test
    fun 上传文件() {
        val path = "/api/wages/uploadToMinio?type=1"
        postFile(
            path,
            "/home/haha/Downloads/甘肃EPC项目测试报告.xlsx"
        )
    }


    @Test
    fun 签名测试() {
        var appKey = ServerApi.PROD_AD.appKey
         appKey = "Cr4UlboSW8iVSnUlxA20ac6"
        var secret = ServerApi.PROD_AD.secret
         secret = "OLcfxalj71jec8hjbnnTsVdsARdMks0R1wNS3YvbvxcXhcBd1o"
        var timestamp = 1668569150929
        var nonce = "123456"
        var sign = getSign(appKey, secret, timestamp.toString(), nonce)
        assert("f729f71e77423c1c21410eb66886b220".equals(sign))
    }
}