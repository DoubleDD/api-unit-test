package com.example.springboot.http.分水江.工具

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import java.io.File


fun main() {
    val json = File("src/test/java/com/example/springboot/http/分水江/工具/山塘.json").readText(Charsets.UTF_8)
    val jsonObject = JSON.parseObject(json)
    val response = jsonObject.getJSONObject("Response")
    val data = response.getJSONObject("Data")
    val resp = data.getJSONObject("Response")
    val textDetections = resp.getJSONArray("TextDetections")

    for ((index, _) in textDetections.withIndex()) {
        val jsonObj = textDetections.getJSONObject(index)
        val DetectedText = jsonObj.getString("DetectedText")
        if ("返回参数".equals(DetectedText)) return
        val ItemPolygon = jsonObj.getJSONObject("ItemPolygon")
        val y = ItemPolygon.getIntValue("Y")
        val x = ItemPolygon.getIntValue("X")
        if (y >= 1516 && x <= 400) {
            println(jsonObj)
            println(DetectedText)
        }
    }

}