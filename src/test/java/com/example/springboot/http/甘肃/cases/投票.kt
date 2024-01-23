package com.example.springboot.http.甘肃.cases

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 投票 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_AD
    }

    @Test
    fun 表单状态() {
        get("/api/formExam/getFormStatus?id=20211208011306917946862227689472")
    }

    @Test
    fun 是否已提交() {
        get("/api/formExam/hasSubmit?deviceId=fd591e1b8886cd82beda99b19d16d6500706&formId=20211208011306917946862227689472")
    }

    @Test
    fun 表单详情() {
        get("/api/formExam/getDetails?id=20211208011306917946862227689472")
    }

    @Test
    fun 提交答案() {
//        get("")
    }
}