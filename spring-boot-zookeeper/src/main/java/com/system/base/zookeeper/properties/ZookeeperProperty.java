package com.system.base.zookeeper.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperProperty {

    String connectString;
    int timeout;

}
