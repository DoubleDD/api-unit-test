package com.example.springboot.http.甘肃.水利年鉴

import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

class 畅写回调 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_YEARBOOK
//        return ServerApi.DEV_YEARBOOK
    }

    @Test
    fun savecallback() {
        val key =
            "JTdCJTIya2V5JTIyJTNBJTIyODQ3NzA0NDA1JUU3JTk0JTk4JUU4JTgyJTgzJUU2JTk5JUJBJUU2JTg1JUE3JUU2JUIwJUI0JUU1JTg4JUE5JUU1JUI5JUIzJUU1JThGJUIwJUU4JUI1JUIwJUU2JTlGJUE1JUU2JTk2JTg3JUU2JUExJUEzMTEtMTguZG9jeCUyMiUyQyUyMnR5cGUlMjIlM0ElMjIxJTIyJTJDJTIyZmlsZVR5cGUlMjIlM0ElMjJhcHBsaWNhdGlvbiUyRnZuZC5vcGVueG1sZm9ybWF0cy1vZmZpY2Vkb2N1bWVudC53b3JkcHJvY2Vzc2luZ21sLmRvY3VtZW50JTIyJTdE"
        val fileUrl =
            "http://60.13.54.71:30119/minio/wanwei-sftp/2022-02-2022:21:02%E7%9C%81%E3%80%81%E5%9C%B0%E3%80%81%E5%8E%BF%E7%BA%A7%E8%A1%8C%E6%94%BF%E5%8C%BA%E5%88%92%E5%9B%BE%EF%BC%88%E9%9B%86%EF%BC%89%E7%BC%96%E5%88%B6%E8%A7%84%E8%8C%83.pdf"
        val path = "/changxie/save/callback"
        var body = """
            
            http://39.100.95.69:30101/oss2/announcement/20939874681973747212%E7%94%98%E8%82%83%E6%99%BA%E6%85%A7%E6%B0%B4%E5%88%A9%E5%B9%B3%E5%8F%B0%E8%B5%B0%E6%9F%A5%E6%96%87%E6%A1%A311-18.docx
            http://39.100.95.69:30101/oss2/announcement/20939874681973747212甘肃智慧水利平台走查文档11-18.docx
            http://39.100.95.69:30101/oss2/announcement/20939874681973747212甘肃智慧水利平台走查文档11-18.docx
            
            
            
            {"actions":[{"type":2,"userid":"e99e1c524100a7f642f0de9feb808ca1"}],"changesurl":"$fileUrl","forcesavetype":"1","history":{"serverVersion":"5.0.16","changes":[{"created":"2022-02-28 07:37:54","user":{"id":"undefined","name":"匿名"}},{"created":"2022-02-28 07:58:08","user":{"id":"e99e1c524100a7f642f0de9feb808ca1","name":"匿名"}}]},"key":"$key","status":6,"url":"$fileUrl","users":["e99e1c524100a7f642f0de9feb808ca1"]}
        """.trimIndent()

        //language=JSON
        body = """
            {
              "actions": [
                {
                  "type": 2,
                  "userid": "undefined"
                }
              ],
              "changesurl": "http://121.37.190.191:8089/cache/files/ZW1wdHklRTUlOUMlQTglRTclQkElQkYlRTclQkMlOTYlRTglQkUlOTE=_8139/changes.zip/changes.zip?md5=W3Bg5CK5uXYEGN-CUospWg&expires=1646046237&disposition=attachment&filename=changes.zip",
              "forcesavetype": "1",
              "history": {
                "serverVersion": "5.0.16",
                "changes": [
                  {
                    "created": "2022-02-28 10:48:51",
                    "user": {
                      "id": "undefined",
                      "name": "匿名"
                    }
                  }
                ]
              },
              "key": "ODEwMDY4NDQ4MTk2NjMzMjA1MTJfNSVFNiU4QSU5NSVFNyVBNSVBOCVFOCVBRSVCMCVFNSVCRCU5NS5kb2N4",
              "status": 6,
              "url": "http://121.37.190.191:8089/cache/files/ZW1wdHklRTUlOUMlQTglRTclQkElQkYlRTclQkMlOTYlRTglQkUlOTE=_8139/output.docx/output.docx?md5=WJysEXkGG7xsabxs0E7XJw&expires=1646046237&disposition=attachment&filename=output.docx",
              "users": [
                "undefined"
              ]
            }
        """.trimIndent()

        //language=JSON
        body = """
            {
              "key": "rc-upload-1649834936699-3",
              "status": 6,
              "url": "http://60.13.54.71:30119/changxie/cache/files/rc-upload-1649834936699-3_7359/output.docx/output.docx?md5=MLM1BOjrnlg63vog23E8-Q&expires=1649836089&disposition=attachment&filename=output.docx",
              "changesurl": "http://60.13.54.71:30119/changxie/cache/files/rc-upload-1649834936699-3_7359/changes.zip/changes.zip?md5=WTwseUyzbxODcSKZ1w1QTw&expires=1649836089&disposition=attachment&filename=changes.zip",
              "history": {
                "serverVersion": "5.0.24",
                "changes": [
                  {
                    "created": "2022-04-13 07:33:02",
                    "user": {
                      "id": "94f3962a638966037ffa9f7d7c26cdc3",
                      "name": "甘肃省-柯栋"
                    }
                  }
                ]
              },
              "users": [
                "94f3962a638966037ffa9f7d7c26cdc3"
              ],
              "actions": [
                {
                  "type": 2,
                  "userid": "94f3962a638966037ffa9f7d7c26cdc3"
                }
              ],
              "userdata": {
                "id": "94f3962a638966037ffa9f7d7c26cdc33",
                "idOriginal": "94f3962a638966037ffa9f7d7c26cdc3",
                "username": "甘肃省-柯栋",
                "indexUser": 3,
                "view": false
              },
              "lastsave": "2022-04-13T07:33:02.000Z",
              "forcesavetype": 1
            }
        """.trimIndent()
        post(path, body, MediaType.APPLICATION_JSON)

    }

    @Test
    fun subtest() {
        val originFileName = "ajlkdsfjaklsdfjldocx"
        val suffix: String = originFileName.substring(originFileName.lastIndexOf(".") + 1)
        println(suffix)
    }

}