package com.example.springboot.http.甘肃.cases

import com.alibaba.fastjson2.JSON
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.apache.commons.lang3.StringUtils
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod

class 缓存接口 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.DEV_THIRD
        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.PROD_THIRD
//        return ServerApi.LOCAL_THIRD
    }


    /**
     * 删除缓存
     */
    @Test
    fun clearCookie() {
        var key = "form_exam:202301121455221063108938960211968:form_status"
        key = "dict_items:"
        key = "dict"
        key = "smart_river_lake_new:uav:userId:"
        key = "third_token:anxinyun_shandan:anxinyun:"
        key = "watermanager"
        val url = "/cache/clear/$key"
        request(url, HttpMethod.DELETE, null)
    }

    /**
     * 查询缓存key
     * 202301121455221063108938960211968
     */
    @Test
    fun keys() {
        var key = "form_exam:202303081240351083006350780534784"
        key = "dict"
        key = "smart_river_lake_new:uav:userId:"
        key = "rongynu_login_"
        key = "dict_items:"
        key = "sms:report_buffer"
        key = "watermanager"

        val url = "/cache/keys/$key"
        get(url)
    }

    /**
     * 获取缓存值
     */
    @Test
    fun value() {
        formatJson.set(true)
        var key = "form_exam:202301121455221063108938960211968:form_status"
        key = "captcha_white_list"
        key = "form_exam:202303081240351083006350780534784:details"
        key = "forget:13277918809"
        key = "sms:13277918809"
        key = "dict_items::com.yunli.ecology.portal.service.DictService:getDictMap:rongyun-config"
        key = "rongynu_login_kedong1"
        key = "ecology_third_rce_cookie"
        key = "ecology_third_rce_token"
        key = "smart_river_lake_new:uav:userId:94f3962a638966037ffa9f7d7c26cdc3"
        key = "third_token:anxinyun_gaotai:anxinyun:"
        key = "iot-kafka-data:shuiku:buffer"
//        key = "iot-kafka-data:shuiku:buffer:error"
//        key = "watermanager:水源地名录:time:startTime"
//        key = "sms:report_buffer"

        val url = "/cache/value?key=$key"
        get(url)
    }

    @Test
    fun 获取短信回执流水号() {
        formatJson.set(true)
        val url = "/cache/value?key=sms:report_buffer"
        var resp = get(url)
        var body = resp.body
        var json = JSON.parseObject(body)
        var result = json.getJSONObject("result")
        var value = result.getJSONObject("value")
        val sarr = arrayListOf<String>()

        for (v in value.values) {
            val report: String = v.toString();
            val map: Map<String, String> = buildParams(report)
            val out = map.get("out").toString()
            val arr = out.split(";")
            for (s in arr) {
                if (StringUtils.isNotBlank(s)) {
                    val outarr = out.split(",")
                    sarr.add("\"" + outarr[0] + "\"")
                }
            }
        }
        println(sarr)
    }

    /**
     * 密码查询
     */
    @Test
    fun 密码查询() {
        // e9776c816f314b2db9860eea18cbd7b1
        val userId = "20211215153912920701537188319232"
        val url = "/cache/value?key=login_$userId"
        get(url)
    }

    /**
     * 获取缓存值
     */
    @Test
    fun values() {
        var key = "dict_items::com.yunli.ecology.portal.service.DictService:getDictMap:portal-desensitization-config"
        key = "all_permission::com.yunli.ecology.portal.service.PermService:getAll:"
        key = "dict::ecology-third-gansu-pre:com.yunli.ecology.service.DictService:getMapByKey:ds-force-update"
        key = "captcha_white_list"
        val url = "/cache/value?key=$key"
        get(url)
    }

    @Test
    fun 获取登录验证码() {
        val id = "4b72e68a1f8143d281b1663fe54371fb"
        val key = "KAPTCHA_SESSION_KEY:$id"
        get("/cache/value?key=$key")
    }
}