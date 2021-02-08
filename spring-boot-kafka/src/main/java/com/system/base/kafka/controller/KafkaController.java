package com.system.base.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.system.base.kafka.producer.KafkaProducer;
import com.system.base.kafka.producer.messages.DemoMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("api/v1.0/kafka")
public class KafkaController {

    @Resource
    private KafkaProducer kafkaProducer;

    @PostMapping
    public void send(@RequestBody DemoMessage demoMessage) throws JsonProcessingException {
        demoMessage.setLogId(UUID.randomUUID().toString());
        kafkaProducer.sendMsg(demoMessage);
    }

}
