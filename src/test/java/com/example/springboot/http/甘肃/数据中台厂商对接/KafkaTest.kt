package com.example.springboot.http.甘肃.数据中台厂商对接

import com.alibaba.fastjson.JSONObject
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.yaml.snakeyaml.util.UriEncoder
import java.io.File

class KafkaTest : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_THIRD
//        return ServerApi.PRE_PROD_THIRD
    }


    @Test
    fun kafka_push_data() {
        @Language("JSON") var data = """
  {
    "观测时间": "2022-06-28 09:08",
    "流水号": "1658",
    "瞬时河道水位、潮位 m": "513",
    "功能码": "32",
    "遥测站分类": "水库(湖泊)",
    "上下行标志": "上行",
    "遥测站分类码": "4B",
    "ctxId": "0cda41fffe1d2e15-00009c32-00000068-5a204f80e9ff37a2-696a99c1",
    "发报时间": "2022-06-28 09:08:51",
    "遥测站状态及报警信息": "交流电充电状态:0-(正常);蓄电池电压状态:0-(正常);水位超限报警状态:0-(正常);流量超限报警状态:0-(正常);水质超限报警状态:0-(正常);流量仪表状态:0-(正常);水位仪表状态:0-(正常);终端箱门状态:0-(开启);存储器状态:0-(正常);IC 卡功能有效:0-(关闭);水泵工作状态:0-(水泵工作);剩余水量报警:0-(未超限);",
    "遥测站地址": "10220001",
    "原始报文": "7E7E0100102200010000320032021658220628090851F1F100102200014BF0F02206280908391A051300FF012AFFFFFFFFFF4520000004003812FFFFFF0208FF03CAE6",
    "中心站地址": "1",
    "密码": "0",
    "信号质量 ": "--",
    "协议": "sl651",
    "功能码描述": "遥测定时报",
    "电源电压 V": "--"
  }
        """.trimIndent()
        data = UriEncoder.encode(data)
        val path = "/iot/kafka/data/shuiku"
        post(path, "data=$data", MediaType.APPLICATION_FORM_URLENCODED)
    }


    @Test
    fun kafkaMockArr() {
        val files = arrayOf(
//            "iot-kafka-data:shuiku:sl651:2F.json",
            "iot-kafka-data:shuiku:sl651:32.json",
//            "iot-kafka-data:shuiku:sl651:33.json",
//            "iot-kafka-data:shuiku:sl651:E9.json",
//            "iot-kafka-data:shuiku:sl651:EB.json"
        )
        for (file in files) {
            val json = File("src/test/java/com/example/springboot/http/甘肃/数据中台厂商对接/$file").readText()
            val arr = JSONObject.parseArray(json)
            for (any in arr) {
                val data = UriEncoder.encode(any.toString())
                val path = "/iot/kafka/test/mock"
                post(path, data, MediaType.APPLICATION_JSON)
            }
        }
    }

    @Test
    fun kafkaMockHash() {
        val json = File("src/test/java/com/example/springboot/http/物联网平台/data_simple.json").readText()
        val hash: JSONObject = JSONObject.parseObject(json)
        for (entry in hash) {
            val data = UriEncoder.encode(entry.value.toString())
            val path = "/iot/kafka/test/mock"
            post(path, data, MediaType.APPLICATION_JSON)
        }
    }

    @Test
    fun getDataFromKafka() {
        get("/iot/kafka/data/shuiku/sl651/32")
    }
}