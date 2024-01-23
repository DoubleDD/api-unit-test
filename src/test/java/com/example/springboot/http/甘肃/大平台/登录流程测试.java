package com.example.springboot.http.甘肃.大平台;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.http.common.BaseTest;
import com.example.springboot.http.common.ServerApi;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class 登录流程测试 extends BaseTest {
    public 登录流程测试() {
        server = getServer();
    }

    private static final int userCount = 10;
    private static final int thinkTime = 200;
    private static final int time      = 20;

    public static void main(String[] args) {

        int           loopCount = time * 1000 / thinkTime;
        CyclicBarrier cb        = new CyclicBarrier(userCount);
        登录流程测试  登录      = new 登录流程测试();

        for (int i = 0; i < userCount; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread() + "等待......");
                    cb.await();
                    System.out.println(Thread.currentThread() + "开始......");
                    String remark = "";
                    for (int j = 0; j < loopCount; j++) {
                        remark = String.format("第%d次登录流程", j);
                        登录.setLocalMsg(remark);
                        登录.流程();
                        登录.clearLocalMsg();
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    private static JSONObject getResult(ResponseEntity<String> resp) {
        String     body = resp.getBody();
        JSONObject json = JSON.parseObject(body);
        return json.getJSONObject("result");
    }

    @Override
    protected ServerApi getServer() {
        return ServerApi.NONE;
    }

    public void 流程() {
        String id   = 获取验证码图片();
        String code = 获取验证码的值(id);
        登录(code);
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ServerApi UN    = ServerApi.PROD_UN;
    private static final ServerApi THIRD = ServerApi.PRE_PROD_THIRD;

    public String 获取验证码图片() {
        ResponseEntity<String> resp   = get(UN.getServer() + "/api/captcha/code");
        JSONObject             result = getResult(resp);
        return result.getString("id");
    }

    public String 获取验证码的值(String id) {
        String                 key    = "KAPTCHA_SESSION_KEY:" + id;
        ResponseEntity<String> resp   = get(THIRD.getServer() + "/cache/value?key=" + key);
        JSONObject             result = getResult(resp);
        return result.getString("value");
    }

    public void 登录(String code) {
        // TODO 登录

    }

}
