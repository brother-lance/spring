package com.system.base.config.controller;

import com.system.base.config.config.DefinitionProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @see
 */
@Slf4j
@RestController
@RequestMapping("/api/v1.0/config")
public class ConfigController {

    @Value("${config.name}")
    String value;

    @Autowired
    DefinitionProperty definitionProperty;

    @GetMapping("{name}")
    public Object getByName(@PathVariable("name") String name) {
        return name + ":" + value;
    }

    @GetMapping
    public Object getObject() {
        return definitionProperty;
    }

}
