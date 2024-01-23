package com.example.springboot.http.甘肃.三方对接

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

/**
 * 云网安全接口单元测试
 */
class 云网接口测试 : BaseTest() {
    @AfterEach
    override fun after() {
        super.after()
        val body = (response.body)
        assert(body.startsWith("{") && body.endsWith("}"))
    }

    override fun getServer(): ServerApi {
//        return ServerApi.DEV_THIRD
//        return ServerApi.LOCAL_THIRD
//        return ServerApi.PROD_THIRD
        return ServerApi.PRE_PROD_THIRD
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6OTYxLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiZDE4ZjllMDg2NDQ1NDA3ZGJkOTQ2ZWQzY2UwZDZiNDgiLCJpYXQiOjE2ODM1MTIzMjUsInVzZXJuYW1lIjoia2Vkb25nMSJ9.MiSPBfyq62phtAdoRdm3T66ntdda6PMnZ68Nac9nGoc
        """.trimIndent()
    }

    override fun commonHeaders(): HttpHeaders {
        var commonHeaders = super.commonHeaders()
        commonHeaders["yw-token"] = "3812d60f-61c0-43c1-9172-93255c957e66-707286"
        return commonHeaders
    }

    /**
     * 获取告警规则
     */
    @Test
    fun 获取告警规则() {
        val url = "/adapter/unicom-cloud/backend/monitor/alarm-strategy/get-rule"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 获取告警数据
     * backend/monitor/alarm-log/list?page=1&limit=1&time_type=1&start_time=1636441146066&end_time=1636527546066
     */
    @Test
    fun 获取告警数据() {
        val url = "/adapter/unicom-cloud/backend/monitor/alarm-strategy/list?page=1&limit=10"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 顶部数据
     */
    @Test
    fun 顶部数据() {
        val url = "/adapter/unicom-cloud/backend/analysis/tenant/index?az_id=Lanzhou"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 24小时告警事件统计卡片
     */
    @Test
    fun 二十四小时告警事件统计卡片() {
        val url =
            "/adapter/unicom-cloud/backend/monitor/alarm-log/list?page=1&limit=10&time_type=1&start_time=1635411534339&end_time=1635497934339"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 安全组件类型
     */
    @Test
    fun 安全组件类型() {
        val url = "/adapter/unicom-cloud/backend/safekit/elegance/machines?page=1&limit=20&expire=0"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 近一小时的高危攻击
     */
    @Test
    fun 近一小时的高危攻击() {
        val url =
            "/adapter/unicom-cloud/backend/monitor/alarm-log/list?page=1&limit=10&time_type=7&start_time=1635501600000&end_time=1635505200000"
        request(url, HttpMethod.GET, null)
    }

    /**
     * 近一小时的高危攻击 弹窗详情
     */
    @Test
    fun 弹窗详情() {
        val url = "/adapter/unicom-cloud/backend/monitor/alarm-log/info?id=70335"
        request(url, HttpMethod.GET, null)
    }


    @Test
    fun test() {
        val endpoint = "/cmsc-statistics/v1/home/resource-quantity"
        val path = "/network/cmsc-statistics?getPath=$endpoint"
        get(path)
    }

    @Test
    fun oneAgg_cpu() {
        val path = "/network/cmsc-statistics"
        val endTime = System.currentTimeMillis()
        val startTime = System.currentTimeMillis() - (7 * 3600 * 1000)
        val body =
            "{\"collection\":\"cpu\",\"equalCondition\":{\"timeType\":1440},\"rangeCondition\":{\"beginTime\":[$startTime,$endTime]},\"returnFields\":{\"averUsage\":0}}"
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun oneAgg_mem() {
        val path = "/network/cmsc-statistics"
        val endTime = System.currentTimeMillis()
        val startTime = System.currentTimeMillis() - (7 * 24 * 3600 * 1000)
        val body =
            "{\"collection\":\"mem\",\"equalCondition\":{\"timeType\":1440},\"rangeCondition\":{\"beginTime\":[$startTime,$endTime]},\"returnFields\":{\"averUsedPercent\":10}}"
        post(path, body, MediaType.APPLICATION_JSON)
    }


    /**
     * 点击各个组件类型要弹窗显示的资产列表
     */
    @Test
    fun 资产列表_堡垒机() {
        val vm_id = "372a06fb-1a91-4296-965e-d73bc2322eea"
        val path = "/adapter/unicom-cloud/backend/strategy/yunxiazi/audit-assets-list?page=1&limit=10&vm_id=$vm_id"
        get(path)
    }

    @Test
    fun 资产列表_Web应用防火墙() {
        val vm_id = "81cf3c9d-df2b-4e2a-845f-667474b9477b"
        val path =
            "/adapter/unicom-cloud/backend/strategy/waf/server-list?page=1&limit=9999&action=server&vm_id=$vm_id&server_name="
        get(path)
        // /adapter/unicom-cloud/backend/strategy/waf/server-list?page=1&limit=9999&action=server&server_name=&vm_id=81cf3c9d-df2b-4e2a-845f-667474b9477b
    }

    @Test
    fun 资产列表_防火墙综合型() {
        val vm_id = "81cf3c9d-df2b-4e2a-845f-667474b9477b"
        val path = "/adapter/unicom-cloud/backend/strategy/vfw/protected-asset?page=1&limit=10&vm_id=$vm_id&hostname="
        get(path)
    }

    @Test
    fun appkey() {
        val name = "appkey测试"
        val description = "123123123123fadfhkasdhf"
        val path = "/adapter/appkey/generate?name=$name&description=$description"
        get(path)
    }
}