package base.eureka.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/eureka/client2")
public class Client2Controller {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping
    public String get() {

        // 通过spring应用命名，获取服务实例ServiceInstance对象
        // ServiceInstance 封装了服务的基本信息，如 IP，端口
        /*
         * 在Eureka中，对所有注册到Eureka Server中的服务都称为一个service instance服务实例。
         * 一个服务实例，就是一个有效的，可用的，provider单体实例或集群实例。
         * 每个service instance都和spring application name对应。
         * 可以通过spring application name查询service instance
         */

        ServiceInstance si = loadBalancerClient.choose("EUREKA-CLIENT");
        StringBuilder sb = new StringBuilder();
        // http://localhost:8081/test
        sb.append("http://").append(si.getHost())
                .append(":").append(si.getPort()).append("/api/v1.0/eureka/client");

        // SpringMVC RestTemplate，用于快速发起REST请求的模板对象。
        /*
         * RestTemplate是SpringMVC提供的一个用于发起REST请求的模板对象。
         * 基于HTTP协议发起请求的。
         * 发起请求的方式是exchange。需要的参数是： URL, 请求方式， 请求头， 响应类型，【URL rest参数】。
         */
        RestTemplate rt = new RestTemplate();

        /*
         * 创建一个响应类型模板。
         * 就是REST请求的响应体中的数据类型。
         * ParameterizedTypeReference - 代表REST请求的响应体中的数据类型。
         */
//        ParameterizedTypeReference<List<Map<String, Object>>> type =
//                new ParameterizedTypeReference<List<Map<String, Object>>>() {
//                };

        /*
         * ResponseEntity:封装了返回值信息，相当于是HTTP Response中的响应体。
         * 发起REST请求。
         */
//        ResponseEntity<List<Map<String, Object>>> response =
//                rt.exchange(sb.toString(), HttpMethod.GET, null, type);
//        /*
//         * ResponseEntity.getBody() - 就是获取响应体中的java对象或返回数据结果。
//         */
//        List<Map<String, Object>> result = response.getBody();
//
        ResponseEntity<String> forEntity = rt.getForEntity(sb.toString(), String.class);

        log.info("调用返回码：{}",forEntity.getStatusCode());

        return forEntity.getBody();
    }
}
