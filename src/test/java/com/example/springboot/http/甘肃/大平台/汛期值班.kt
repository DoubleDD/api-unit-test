package com.example.springboot.http.甘肃.大平台

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test

class 汛期值班 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_PORTAL
    }


    @Test
    fun 导入(){
        val path ="/api/floodSeasonDuty/importData?isFloodSeason=0"
        postFile(path,"/home/kedong/Downloads/导入汛期模板.xlsx")
    }

}