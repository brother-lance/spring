package com.system.base.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.base.kafka.producer.messages.DemoMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@Component
public class KafkaConsumer {

    @Resource
    ObjectMapper objectMapper;

    @KafkaListener(topics = {"DEMO_TOPIC"}, groupId = "test-consumer-group")
    public void onMessage(ConsumerRecord<?, ?> consumerRecord) throws JsonProcessingException {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            log.info("record:{}", consumerRecord);
            log.info("message:{}", msg);
            DemoMessage demoMessage = objectMapper.readValue(msg.toString(), DemoMessage.class);
            log.info("接收对象：{}", demoMessage);
        }
    }

}
