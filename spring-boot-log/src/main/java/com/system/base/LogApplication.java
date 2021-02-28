package com.system.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * </p>
 *
 * @author wushengbin
 * @Date 2021/2/28 11:55
 **/
@Slf4j
@SpringBootApplication
public class LogApplication {

    public static void main(String[] args) {
        log.info("开始启动系统...");
        SpringApplication.run(LogApplication.class, args);
    }
}
