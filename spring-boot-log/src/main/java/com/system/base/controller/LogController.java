package com.system.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author wushengbin
 * @Date 2021/2/28 11:58
 **/
@Slf4j
@RestController
@RequestMapping("log")
public class LogController {

    @GetMapping
    public String hello() {
        log.info("测试");
        return "hello";
    }
}
