package com.keeton.spring.custom.annotation;

import com.keeton.spring.custom.annotation.v2.KeetonScan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@KeetonScan("com.keeton.spring.custom.annotation")
@SpringBootTest
class DefaultProxyCreatorApplicationTest {

    @Autowired
    private Test2Service test2Service;
    @Autowired
    private TestMapper testService;

    @Test
    void test1() {
        testService.selectByPrimaryKey("张三", 13);
    }

    @Test
    void test2() {
        testService.selectByPrimaryKey("张三", 13);
        test2Service.a();
        System.out.println("\n");
        test2Service.b();
        System.out.println("\n");
        test2Service.c();
    }
}
