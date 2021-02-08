package com.system.base.config.controller;

import com.system.base.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfigControllerTest {

    private URL base;
    private final RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://127.0.0.1:8080/api/v1.0/config");
        System.out.println("请求地址：" + this.base.toString());
    }

    @Test
    public void getByName() {
        Map<String, Object> uri = new HashMap<>();
        uri.put("name", "张三");
        ResponseEntity<String> forEntity = restTemplate.getForEntity(this.base + "/{name}", String.class, uri);
        System.out.printf("返回码：%s\n", forEntity.getStatusCode());
        System.out.printf("测试结果为：%s%n", forEntity.getBody());
    }

    @Test
    public void getObject() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(this.base.toString() , String.class);
        System.out.printf("返回码：%s\n", forEntity.getStatusCode());
        System.out.printf("测试结果为：%s%n", forEntity.getBody());
    }

}
