package com.system.base.zookeeper.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
        ZookeeperProperty.class
})  // 增加允许配置
public class PropertyConfig {
}
