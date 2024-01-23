package com.example.springboot.http.甘肃.数据中台厂商对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod

class 缓存接口 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.DEV_THIRD
//        return ServerApi.LOCAL_THIRD
    }


    /**
     * 删除缓存
     */
    @Test
    fun clearCookie() {
        val url = "/cache/clear/third_data"
        request(url, HttpMethod.DELETE, null)
    }

    /**
     * 查询缓存key
     */
    @Test
    fun keys() {
        val key = "third_token:anxinyun"
        val url = "/cache/keys/$key"
        get(url)
    }

    /**
     * 获取缓存值
     */
    @Test
    fun value() {
        val url = "/cache/value/qingyang_mqtt"
        get(url)
    }

    /**
     * 获取缓存值
     */
    @Test
    fun values() {
        var key = "dict_items::com.yunli.ecology.portal.service.DictService:getDictMap:portal-desensitization-config"
        key = "iot-kafka-data:shuiku:buffer"
        val url = "/cache/value?key=$key"
        get(url)
    }

}