package com.keeton.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LokiTest implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.debug("程序启动...");
        log.debug("logback-spring.xml 读取yaml文件配置测试");
    }
}
