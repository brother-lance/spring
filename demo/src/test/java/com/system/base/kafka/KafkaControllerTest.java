package com.system.base.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.system.base.SpringBaseTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class KafkaControllerTest extends SpringBaseTest {

    private URL url;
    private final RestTemplate restTemplate = new RestTemplate();


    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void postKafka() throws JsonProcessingException, MalformedURLException {

        url = new URL(this.baseUrlString + "kafka");
        System.out.println("发送地址：" + url.toString());

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", "1");
        dataMap.put("info", "测试信息");

        // 方式一
        ResponseEntity<String> response = restTemplate.postForEntity(this.url.toString(), dataMap, String.class);
        System.out.printf("返回码：%s\n", response.getStatusCode());
        System.out.printf("测试结果为：%s%n", response.getBody());

    }


}
