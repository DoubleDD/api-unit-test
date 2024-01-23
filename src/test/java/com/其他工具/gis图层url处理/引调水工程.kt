package com.其他工具.gis图层url处理

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import java.io.File


class yds : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.NONE
    }

    /**
     * 获取引调水工程列表
     */
    fun getNameList(): List<String> {
        val fileName =
            "/home/kedong/com.gitee/spring-boot-components/spring-boot/src/test/java/com/其他工具/gis图层url处理/gis图层信息.json"
        val body = File(fileName).readText()
        val json = JSON.parseObject(body)
        val result: JSONObject = json.getJSONObject("result")
        val jsonArray: JSONArray = result.getJSONArray("data")
        val list: MutableList<String> = mutableListOf()
        for (i in jsonArray) {
            val item: JSONObject = i as JSONObject
            val layerName: String = item.getString("layerName")
            val creatorId = item.getString("creatorId")
            val id = item.getString("id")
            var url = "http://60.13.54.71:31607/cim-platform/api/sharelink/$creatorId/getwms/$id?service=WMS"


            val detailUrl = "http://60.13.54.71:31607/cim-platform/api/share/v1.6/detail/$id"
            val responseEntity = get(detailUrl, headers())
            val resp = responseEntity.body
            val respJson = JSON.parseObject(resp)
            val rr = respJson.getJSONObject("result")
            val shareWms = rr.getString("shareWms")
            val shareWfs = rr.getString("shareWfs")
            if (shareWms != null && "" != shareWms.trimIndent()) {
                list.add("$layerName, http://60.13.54.71:31607$shareWms")
            } else if (shareWfs != null && "" != shareWfs.trimIndent()) {
                list.add("$layerName, http://60.13.54.71:31607$shareWfs")
            } else {
                list.add(layerName)
            }
        }
        return list
    }

    /*

    http://60.13.54.71:31607/cim-platform/api/sharelink/20210508134141840584246283341824/getwms/0c274532-b82f-4674-8d97-8aeecb9faabd?service=WMS
     */
    @Test
    fun list() {
        val list = getNameList()
        val fileName =
            "/home/kedong/com.gitee/spring-boot-components/spring-boot/src/test/java/com/其他工具/gis图层url处理/引调水工程图层地址.csv"
        val file = File(fileName)
        file.setWritable(true)
        file.writeText("")
        list.forEach { v -> file.appendText(v + "\r\n") }

    }
}

fun headers(): Map<String, String> {
    val h = HashMap<String, String>()
    h["Cookie"] = """
    yl-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6OTIwLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiYjM2NWIyMTJlNjhhNDc4OWE5OGNlMTAwZmU4NWRlOGMiLCJpYXQiOjE2ODExOTY4NTYsInVzZXJuYW1lIjoia2Vkb25nMSJ9.sK6DQHSrl-Cfb31qQ0qXl5zAgrpmIJxwbUqdcZ-7DIc; JSESSIONID=6DD4C78AE6162F84035D05D92666DA88; app-cim-platform-gateway-token=eyJraWQiOiIyMDIxMDUwODEzNDE0MTg0MDU4NDI0NjI4MzM0MTgyNCIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2Iiwic2lkIjoiMjQyODg3ZmUyZGMxNGE2NDk2NTU0ZTg4MzIzNGZjYzQifQ.eyJyb2xlSWRzIjpbIjIwMjIxMjA1MDAxODMzMTA0OTExNzUzOTM2NTQyOTI0OCIsIjIwMjIxMDE4MTYxNjUyMTAzMTk2NDA5MzY1MDcwNjQzMiIsIjIwMjIwNDI0MTkwMzIyOTY3ODYzMzQxNzYwNTIwMTkyIl0sIm9yZ0lkcyI6WyI4NTNkNjNlYmIyMDdlNjQ4NDYzZDE4NmMxMjYyNTI0MSJdLCJucyI6ImRlZmF1bHQiLCJsb2dpbkF0IjoxNjgxMTk2OTE0MTEzLCJ1c2VybmFtZSI6ImFkbWluIn0.Kx7PZaE-1zjYSmDC2OCFSHWg3nEwLpBtEGNwqWocAJY
""".trimIndent()
    return h;
}