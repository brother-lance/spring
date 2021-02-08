package com.system.base.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.base.kafka.producer.messages.DemoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topicUser;//topic名称

    @Resource
    ObjectMapper objectMapper;

    public void sendMsg(DemoMessage demoMessage) throws JsonProcessingException {
        String data = objectMapper.writeValueAsString(demoMessage);
        log.info("kafka send message:{}", data);
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topicUser, data);
        log.info("return result :{}", result);
    }

}
