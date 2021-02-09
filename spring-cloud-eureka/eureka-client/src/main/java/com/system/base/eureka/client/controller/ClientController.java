package com.system.base.eureka.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/api/v1.0/eureka/client")
public class ClientController {

    @GetMapping
    public String get(){
        return "测试";
    }
}
