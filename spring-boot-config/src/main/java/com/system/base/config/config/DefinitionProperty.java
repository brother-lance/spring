package com.system.base.config.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix= "definition")
public class DefinitionProperty {

    private String name;

    DefinitionOtherConfigProperties config = new DefinitionOtherConfigProperties();

}
