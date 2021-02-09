package com.system.base.consumer.controller;

import com.system.base.Application;
import com.system.base.consumer.vo.DemoVO;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

public class DemoTest {

    private static URL url;
    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        try {
            url = new URL("http://localhost:8081/api/v1.0/dubbo-consumer");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DemoVO demoVO = new DemoVO();
        demoVO.setId("1");
        demoVO.setName("名称");
        demoVO.setMessage("信息");

        // 方式一
        ResponseEntity<DemoVO> response = restTemplate.postForEntity(url.toString(), demoVO, DemoVO.class);
        System.out.printf("返回码：%s\n", response.getStatusCode());
        System.out.printf("测试结果为：%s%n", response.getBody());

    }
}
