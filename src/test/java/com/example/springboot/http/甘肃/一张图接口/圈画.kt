package com.example.springboot.http.甘肃.一张图接口

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 圈画 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.LOCAL_FS
        return ServerApi.DEV_FS
    }

    /**
     *
     * curl 'http://60.13.54.71:30119/yunli/fts/v1/api/ogsHandler/listCdataTypeItemByGisRegion' \
     * -X 'POST' \
     * -H 'Content-Type: application/json;charset=utf-8' \
     * -H 'Pragma: no-cache' \
     * -H 'Accept: application/json, text/plain'
     * -H 'Accept-Language: zh-CN,zh-Hans;q=0.9' \
     * -H 'Accept-Encoding: gzip, deflate' \
     * -H 'Cache-Control: no-cache' \
     * -H 'Origin: http://60.13.54.71:30119' \
     * -H 'Content-Length: 267' \
     * -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.5.1 Safari/605.1.15' \
     * -H 'Referer: http://60.13.54.71:30119/yl-gansu_onemap/' \
     * -H 'Connection: keep-alive' \
     * -H 'Host: 60.13.54.71:30119' \
     * -H 'Cookie: yl-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJiMjRkZjM0MmUyZDY0MjZmODRlODA0NjE5NmZkNTk0NyIsInJhbmRvbSI6MzgzLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiNGM5MmM0NzdkMDE4ZGQyMWI3N2IyZjNhY2NlMWNlNjciLCJ1dUlkIjoiNzc4ODllZDVhZmFhNGJhMWIyMmEwZTQ1YjIzN2Q2M2UiLCJpYXQiOjE2ODg2MTI2ODgsInVzZXJuYW1lIjoiZGF4aW9uZyJ9.q7JZtD87HO4cXID8M6UqTwT5KaZU8-Zm-qZfpzDKXHo; app-cim-platform-gateway-token=eyJraWQiOiI0YzkyYzQ3N2QwMThkZDIxYjc3YjJmM2FjY2UxY2U2NyIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2Iiwic2lkIjoiZWMxMWNjY2UwNzlmNDUxYzgwOWRhMzRiMWEzOGM3MjMifQ.eyJyb2xlSWRzIjpbImZlMTIwZDFkMWYxNzQ1YTQ4ZjU5Y2M5YmMwODhjZWM5IiwiNmQ1YjEyYzMyZjIxNGE4NWJjMmU0ZjAyZTI5MjYwNTEiLCJhZTU5YTZjNDQ5Zjg0OGEwYWE2OTZiY2Y2NTJjYzkxYiIsIjk5ZmEwOGM3ZGYwZDQxZmM5NDkxYmE5YmM4NTVkZGJhIl0sIm9yZ0lkcyI6WyI4NTNkNjNlYmIyMDdlNjQ4NDYzZDE4NmMxMjYyNTI0MSJdLCJucyI6ImRlZmF1bHQiLCJsb2dpbkF0IjoxNjg4NjEyNjkwNDM3LCJ1c2VybmFtZSI6ImRheGlvbmcifQ.6Zw8duqOl8NaN2l2zk6J6Fyki68KYnjuwiBShwapto0' \
     * -H 'Proxy-Connection: keep-alive' \
     * -H 'token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJiMjRkZjM0MmUyZDY0MjZmODRlODA0NjE5NmZkNTk0NyIsInJhbmRvbSI6MzgzLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiNGM5MmM0NzdkMDE4ZGQyMWI3N2IyZjNhY2NlMWNlNjciLCJ1dUlkIjoiNzc4ODllZDVhZmFhNGJhMWIyMmEwZTQ1YjIzN2Q2M2UiLCJpYXQiOjE2ODg2MTI2ODgsInVzZXJuYW1lIjoiZGF4aW9uZyJ9.q7JZtD87HO4cXID8M6UqTwT5KaZU8-Zm-qZfpzDKXHo' \
     * --data -binary '{"cdataTypes":"HP","coordinates":[[[99.91737108322293,38.72031336205237],[100.20201075673074,38.9274341457538],[100.43093372819021,38.8196344396168],[100.35220360573061,38.474433133447754],[100.09905598120663,38.4913903905929],[99.91737108322293,38.72031336205237]]]}'
     *
     */
    @Test
    fun 圈画导出() {
        val path = "/api/ogsHandler/listCdataTypeItemByGisRegion/export"
        val body = """
           {"cdataTypes":"WI_UNIT","coordinates":[[[102.60336132155227,37.443527439699174],[103.79429565177726,37.39845425830841],[103.29236382053661,35.844525182709695],[102.60336132155227,37.443527439699174]]]}
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)
    }
}