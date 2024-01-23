package com.example.springboot.http.甘肃.水利年鉴

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.example.springboot.http.common.BaseTest
import com.example.springboot.http.common.ServerApi
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

class 缓存接口 : BaseTest() {
    override fun getServer(): ServerApi {
//        return ServerApi.DEV_THIRD
        return ServerApi.PRE_PROD_THIRD
//        return ServerApi.LOCAL_THIRD
    }


    /**
     * 删除缓存
     */
    @Test
    fun clearCookie() {
        val key = "dept_tree::"
        val url = "/cache/clear/$key"
        request(url, HttpMethod.DELETE, null)
    }

    /**
     * 查询缓存key
     */
    @Test
    fun keys() {
        val key = "dept_tree::"
        val url = "/cache/keys/$key"
        get(url)

    }

}

class 水利年鉴部门 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.LOCAL_YEARBOOK
//        return ServerApi.DEV_YEARBOOK
//        return ServerApi.PROD_YEARBOOK
    }

    override fun getToken(): String {
        return """
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiIzOTY4ZGNhZGVlYjc0MGNhYTc2ODQ0ZjI3OTQyMGQxMCIsInJhbmRvbSI6ODMyLCJncm91cElkIjoiMDEyNDEyZDJhN2MyNDYxZTE5M2YzNjc2MzNmMTNjMTgiLCJhcHBJZCI6ImdhbnN1X2FwcGx5IiwidXNlcklkIjoiOTRmMzk2MmE2Mzg5NjYwMzdmZmE5ZjdkN2MyNmNkYzMiLCJ1dUlkIjoiODg2YWI1ZjQ4NGZmNDJiZWI3MDRhMzgxNzNlMTg4ZDkiLCJpYXQiOjE2NTc2MDIwNjAsInVzZXJuYW1lIjoia2Vkb25nMSJ9.MpbA4BqnVWruoFleO-Z-lykg6eu9GnTWjYczuu2XT40
        """.trimIndent()
    }

    @Test
    fun 管理员分配部门树() {
        get("/manuscripts/admin/treeAll")
    }

    @Test
    fun 任务分配部门树() {
        val userId = "312c76fa2c754fb4b4cdecaea08bc6a8"
        val manuscriptsId = "1546377480739041281"
        val missionPhase = 1
        get("/manuscripts/task/treeAll?manuscriptsId=$manuscriptsId&missionPhase=$missionPhase&targetUserId=$userId")
    }


    @Test
    fun 部门树() {
        val responseEntity = get("/manuscripts/treeAll")
        val body = responseEntity.body
        val json = JSON.parseObject(body)
        val deptArr: JSONArray = json.getJSONArray("result")
        val result: MutableList<MutableMap<String, Any>> = ArrayList()

//        buildTree(deptArr, result)
    }

}

class 大平台服务 : BaseTest() {
    override fun getServer(): ServerApi {
        return ServerApi.PRE_PROD_PORTAL
    }

    @Test
    fun 构建部门树() {
        val responseEntity = post("/api/department/treeAll2", "[]", MediaType.APPLICATION_JSON)
        val body = responseEntity.body
        val json = JSON.parseObject(body)
        val deptArr: JSONArray = json.getJSONArray("result")
        val result: MutableList<MutableMap<String, Any>> = ArrayList()

        buildTree(deptArr, result)
    }
}

fun buildTree(deptArr: JSONArray, result: MutableList<MutableMap<String, Any>>) {
    getDept(deptArr, result, 0, "")
    println(
        """ 
        ======================================= 部门数据sql ================================
            
            
            
            
        delete from department; 
        """.trimIndent()
    )
    for (dept in result) {
        val id = dept["id"] as String
        val level = dept["level"] as Int
        val treePath = dept["treePath"] as String
        val name = dept["name"] as String
        println(treePath)
        if (deptIds.contains(id)) {
            val sql = """
                    insert into department (id,`level`,tree_path,name,enable) value ('$id','$level','$treePath','$name',1);
                """.trimIndent()

            println(sql)
        }
    }
}

fun getDept(deptArr: JSONArray, result: MutableList<MutableMap<String, Any>>, level: Int, parentTreePath: String) {

    for (dept in deptArr) {
        val str = JSON.toJSONString(dept)
        val jobj = JSON.parseObject(str)
        val id = jobj.getString("id")
        val name = jobj.getString("name")
        val map: MutableMap<String, Any> = HashMap()

        map["id"] = id
        map["name"] = name
        map["level"] = level
        map["treePath"] = "$parentTreePath/$name"
        result.add(map)

        val children: JSONArray = jobj.getJSONArray("children")
        getDept(children, result, level + 1, map["treePath"] as String)
    }
}

val deptIds = arrayOf(
    "b2eaf6a6a30f44eb9406c9bc0bbfa280",// 庆阳市
    "9a73474b34b9471481d7f2f2cfde90ad",// 定西市
    "816fbf3349234e16b9da11055e07bb7f",
    "31e27fc160f54bee8e9d6a3401729164",
    "a29d563962a24565a839e4c22a54c171",
    "c5c0becdabc2388d8b904db4f3a2917c",
    "cc3743f640cb4bb3b148790c485f9d12",
    "921c1edfbd634f23b5d6051cd0bb1595",
    "7516344222184945ae650e9f2afe7e67",
    "fcd710051ed3f657ef9c76f87156faa5",
    "d5a5e1a7f025425fa9f43a21bd0db68b",
    "72a342e5e818435ea4dd3331a6f34009",
    "30209c25d0b513e88dfb92cf2e9bc66c",
    "c2f862df96fa4889a9d9afc42ff4a0c3",
    "f05735bbbfc04ce0ad1dd0748ab4fcff",
    "9875d6c4952b4765a6657a3cc567d110",
    "fdd7cbcd94494e7bbaad601b305e53f3",
    "b2c39383c4a24c12a371e8c672858515",
    "195b8ec690a09056a135301a3ddb09e1",
    "e96633f4835c4868be0114c40fd70806",
    "633794ea5b083c016a051b43e4e2a500",
    "107a08350fb612ff8851452bbc216997",
    "aa600cc15d3c499099fedc041b854013",
    "8d99d7954a344a7487e3fc50c94f94ca",
    "c1a045ac54af47558342813dba1cdbca",
    "c41a77706f4b2485015ee9d5c42e8803",
    "178e8baaae7b7819f23f962121d40eca",
    "95cc3ff799c049ae81e64eab4dc505c4",
    "b0ec3bf289ee47d8aecc1081f2e952d8",
    "d1f9add71aa74e929324a1c67d54e24f",
    "d44180e8ba034633b7a78e812599f77a",
    "942ce66587f9400f9c3d24a0c9db2198",
    "86d30b28df2d423487c69df360d35576",
    "8ef68165c32bd58f1e2305de83154e08",
    "858371bc9dfcd086481fe7fc6e8cbd41",
    "17d716f6acd8b3c51fe34fc17b3bdfde",
    "930add1d7a59470abfdd6521fa335b7f",
    "b02826963c7cc8ae222db3b6b22ea324",
    "32831369fc524d49ab406528e533cb77",
    "7acb311b64c34f61ba38d0fae523aefe",
    "b145b2e84b3b4daa9442a0201078f026",
    "99987b44f9fc4a32967475a8bee8af0d",
    "b8a404dd89bc4e1eba07250f3c1acea1",
    "c2199144b63d46a6a82c52b086a6bb8f",
    "3e104403fd874510a4899ac2a5206f5e",
    "6502e6411ea14cbba33e042e4375f65b",
    "11879d8bff4149e395b3814d4f7334ee",
    "95907fd96db24b7c9e7a6850db50b1f9",
    "051ace4aee17429cb30e8fd9fe5dd191",
    "d7b2c6cd89de4c099c9fdb7d589e6e2d",
    "f2e6063d80c44690a8865b78d1151a8e",
    "30d5876b06814281bc20be1163efb1e2",
    "813ca83ffeba47419c0165d71d18b7c0",
    "e6c36ca12587491b9bb01c315c9ec097",
    "de4c302132e344dfaa06133c48a7ccc2",
    "9f0a2639fc174478b6b05e9921706dcf",
    "8e87d99647004788b0da2694c9e5aea5",
    "c513d106ef7653d08111d598cb102ec8",
    "38fc2ef53acb4596b40c0d7c6f7b371c",
    "17491b96dc714dada6318383b017f4ac",
    "98b1e8e138b24111ae9498226e8fdedc",
    "248315cd2e4448f29f821db5e3a01fa9",
    "c9fa9b65e6534ca8adcfdbf3d599f782",
    "5f6c91347c634e7f8e70d1d3eb8b1dd9",
    "e58e34da4ce34b4db314202299744d72",
    "4f9c1c86e15f4704b84dfefb23e08d3f",
    "b5cd1f4cced746c4a83b8972df67d6a8",
    "86afe817470245348a667423c41b44d2",
    "4b010276f933431cb6bd1fbe37cb2891",
    "4a0df5e927c549549e1a1c2f6606825f",
    "d3bdf1b39674074a8bcb16dcc278b95f",
)


