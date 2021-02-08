package con.system.base.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.base.Application;
import com.system.base.web.reponse.Response;
import com.system.base.web.vo.DemoVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerTest {

    private URL base;
    private final RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://127.0.0.1:8080/api/v1.0/demo");
        System.out.println("请求地址：" + this.base.toString());
    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void add() throws JsonProcessingException {

        DemoVO demoVO = new DemoVO();
        demoVO.setAge(18);
        demoVO.setName("名称");

        // 方式一
        ResponseEntity<String> response = restTemplate.postForEntity(this.base.toString(), demoVO, String.class);
        System.out.printf("返回码：%s\n", response.getStatusCode());
        System.out.printf("测试结果为：%s%n", response.getBody());

        // 方式二
        String data = new ObjectMapper().writeValueAsString(demoVO);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> request = new HttpEntity<>(data, headers);

        response = restTemplate.postForEntity(this.base.toString(), request, String.class);
        System.out.printf("返回码：%s\n", response.getStatusCode());
        System.out.printf("测试结果为：%s%n", response.getBody());
    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void delete() {
        restTemplate.delete(this.base.toString() + "/{id}", 1);
    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void update() {
        DemoVO demoVO = new DemoVO();
        demoVO.setId("1");
        demoVO.setAge(18);
        demoVO.setName("名称");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(this.base.toString(), demoVO);
    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void list() {
        RestTemplate restTemplate = new RestTemplate();

        ParameterizedTypeReference<Response<List<DemoVO>>> typeRef = new ParameterizedTypeReference<Response<List<DemoVO>>>() {
        };

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        Map<String, Object> data = new HashMap<>();
        data.put("", "");

        HttpEntity<String> request = new HttpEntity(data, headers);

        ResponseEntity<Response<List<DemoVO>>> responseEntity = restTemplate.exchange(this.base.toString(), HttpMethod.GET, request, typeRef);
        System.out.println("返回码:" + responseEntity.getStatusCode());
        System.out.println("返回数据:" + responseEntity.getBody());
    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void get() {

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", "1");

        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(this.base.toString() + "/{id}", Response.class, uriVariables);
        System.out.println("返回码:" + responseEntity.getStatusCode());
        System.out.println("返回数据:" + responseEntity.getBody());


        long startTime = System.currentTimeMillis();
        responseEntity = restTemplate.getForEntity(this.base.toString() + "/callable/{id}", Response.class, uriVariables);
        System.out.println("返回码:" + responseEntity.getStatusCode());
        System.out.println("返回数据:" + responseEntity.getBody());
        System.out.println("执行时间:" + (System.currentTimeMillis() - startTime));

    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void callable() {

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", "1");
        long startTime = System.currentTimeMillis();
        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(this.base.toString() + "/callable/{id}", Response.class, uriVariables);
        System.out.println("返回码:" + responseEntity.getStatusCode());
        System.out.println("返回数据:" + responseEntity.getBody());
        System.out.println("执行时间:" + (System.currentTimeMillis() - startTime));

    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void asyncContext() {

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", "1");
        long startTime = System.currentTimeMillis();
        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(this.base.toString() + "/AsyncContext/", Response.class, uriVariables);
        System.out.println("返回码:" + responseEntity.getStatusCode());
        System.out.println("返回数据:" + responseEntity.getBody());
        System.out.println("执行时间:" + (System.currentTimeMillis() - startTime));

    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void webAsyncTask() {

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", "1");
        long startTime = System.currentTimeMillis();
        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(this.base.toString() + "/webAsyncTask/{id}", Response.class, uriVariables);
        System.out.println("返回码:" + responseEntity.getStatusCode());
        System.out.println("返回数据:" + responseEntity.getBody());
        System.out.println("执行时间:" + (System.currentTimeMillis() - startTime));

    }

    /**
     * 向"/api/v1.0/demo"地址发送请求，并打印返回结果
     */
    @Test
    public void deferred() {

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", "1");
        long startTime = System.currentTimeMillis();
        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(this.base.toString() + "/deferred/{id}", Response.class, uriVariables);
        System.out.println("返回码:" + responseEntity.getStatusCode());
        System.out.println("返回数据:" + responseEntity.getBody());
        System.out.println("执行时间:" + (System.currentTimeMillis() - startTime));

    }


}
