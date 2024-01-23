package com.example.springboot.http.泛生态产品

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.apache.ibatis.annotations.Lang
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class SeaTunnel测试 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.SEATUNNEL_LOCAL
    }


    @Test
    fun 提交任务() {
        val jobId = 123;
        val jobName = "mysqlToEs"

        @Language("JSON")
        val body = """
            {
              "env": {
                "job.mode": "STREAMING"
              },
              "source": [
                {
                  "plugin_name": "MySQL-CDC",
                  "result_table_name": "xxl_job_log",
                  "server-id": 5656,
                  "username": "root",
                  "password": "123456",
                  "table-names": [
                    "xxl_job.xxl_job_log"
                  ],
                  "base-url": "jdbc:mysql://mariadb:3306/test"
                }
              ],
              "transform": [
                {
                  "plugin_name": "ONEMAP-MYSQL-ES",
                  "source_table_name": "xxl_job_log",
                  "query": "SELECT id, job_id, trigger_msg as msg FROM xxl_job_log"
                }
              ],
              "sink": [
                {
                  "plugin_name": "Elasticsearch",
                  "hosts": [
                    "es:9200"
                  ],
                  "username": "",
                  "password": "",
                  "index": "index_xxl_job_log",
                  "primary_keys": [
                    "id"
                  ]
                }
              ]
            }
        """.trimIndent()
        post("/hazelcast/rest/maps/submit-job?jobId=${jobId}&jobName=${jobName}", body, MediaType.APPLICATION_JSON)
    }
}