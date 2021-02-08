package com.system.base.kafka.producer.messages;

import lombok.Data;

@Data
public class DemoMessage {

    private String id;

    private String logId;

    private String info;

}
