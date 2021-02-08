package com.system.base.config.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
        DefinitionProperty.class
})  // 增加允许配置
public class PropertiesConfig {

}