package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import java.util.concurrent.BrokenBarrierException
import java.util.concurrent.CyclicBarrier

class 智能助手 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.PRE_PROD_PORTAL
        return ServerApi.LOCAL_PORTAL
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6NjkzLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiNzgyMTA0MDIzOGRkNGI4N2E3ZTYzMWIzNDNjMWYwMzYiLCJpYXQiOjE2ODMzODMxNjAsInVzZXJuYW1lIjoia2Vkb25nMSJ9.0HrVM3oRI3Cdrpt0E1Z_G-8Um0edIZshm0GWx49leyA
       """.trimIndent()
    }

    @Test
    fun 统计() {
        val path = "/api/intelligentAssistant/queryUserUseStatistics"
        val body = """
            {"deptId":"816fbf3349234e16b9da11055e07bb7f","regionId":"","startTime":"2022-03-08","endTime":"2022-04-08","type":"2","userIds":[],"pageNum":1,"pageSize":5}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 操作习惯分析() {
        val path = "/api/intelligentAssistant/queryCardAnalyse"
        val body = """
            {"deptId":"0","regionId":"","startTime":"2022-11-16","endTime":"2022-11-30","type":"2","userIds":[]}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }

    @Test
    fun 首页布局() {
        val path = "/api/layer/config/queryLayerConfig?type=1"
        for (i in 1..10000)
            get(path)
    }

    @Test
    fun 根据id查询用户列表() {
        val path = "/api/intelligentAssistant/queryUserIds"
        post(path, "deptId=0&regionId=&type=2", MediaType.APPLICATION_FORM_URLENCODED)
    }


    fun 根据id查询用户列表(body: String) {
        val path = "/api/intelligentAssistant/queryUserIds/v2"
        post(path, body, MediaType.APPLICATION_FORM_URLENCODED)
    }


}

class 智能助手并发测试 {

    private val userCount = 10
    private val thinkTime = 1000
    private val time = 1


    fun 根据id查询用户列表() {
        // 每个线程循环次数
        val loopCount = time * 1000 / thinkTime
        val cb = CyclicBarrier(userCount)
        for (i in 0 until userCount) {
            Thread {
                try {
                    println(Thread.currentThread().toString() + "等待......")
                    cb.await()
                    println(Thread.currentThread().toString() + "开始......")
                    智能助手().根据id查询用户列表("deptId=$i")
//                    智能助手().根据id查询用户列表("deptId=$i")
//                    智能助手().根据id查询用户列表("deptId=0&auth=true&type=2")
                } catch (e: InterruptedException) {
                    throw RuntimeException(e)
                } catch (e: BrokenBarrierException) {
                    throw RuntimeException(e)
                }
            }.start()
        }
    }
}

fun main(args: Array<String>) {
    智能助手并发测试().根据id查询用户列表()
}