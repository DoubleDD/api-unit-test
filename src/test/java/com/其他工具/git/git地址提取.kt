package com.其他工具.git

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import java.io.File

fun extraGitUrl() {
    val userHome = System.getProperty("user.home")
    val fileName = "$userHome/com.gitee/spring-boot-components/spring-boot/src/test/java/com/其他工具/git/gansu_server.json"
    val body = File(fileName).readText()
    val jsonArray = JSON.parseArray(body)
    for (item in jsonArray){
        val obj:JSONObject = item as JSONObject
        val project = obj.getJSONObject("project")
        val webUrl = project["web_url"]
        println(webUrl)
    }
}
fun main() {
    extraGitUrl()
}