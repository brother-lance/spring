package com.system.base.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.base.Application;
import com.system.base.kafka.producer.messages.DemoMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KafkaControllerTest {

    private URL base;
    private final RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://127.0.0.1:8080/api/v1.0/kafka");
        System.out.println("请求地址：" + this.base.toString());
    }

    @Test
    public void sendTest() throws JsonProcessingException {

        DemoMessage message = new DemoMessage();
        message.setId("1");
        message.setInfo("测试-1");

        // 方式一
        ResponseEntity<String> response = restTemplate.postForEntity(this.base.toString(), message, String.class);
        System.out.printf("返回码：%s\n", response.getStatusCode());
        System.out.printf("测试结果为：%s%n", response.getBody());

        // 方式二
        String data = new ObjectMapper().writeValueAsString(message);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> request = new HttpEntity<>(data, headers);

        response = restTemplate.postForEntity(this.base.toString(), request, String.class);
        System.out.printf("返回码：%s\n", response.getStatusCode());
        System.out.printf("测试结果为：%s%n", response.getBody());
    }

}
