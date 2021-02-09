package com.system.base.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.base.api.dto.DemoDTO;
import com.system.base.api.facade.DemoFacade;
import com.system.base.consumer.vo.DemoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    ObjectMapper objectMapper;

    @Reference(version = "1.0.0", group = "demoGroup", check = false)
    private DemoFacade demoFacade;

    @GetMapping
    public DemoVO get(DemoVO demoVO) {
        log.info("consumer param:{}", demoVO);
        String logId = UUID.randomUUID().toString();
        return objectMapper.convertValue(demoFacade.call(logId,
                objectMapper.convertValue(demoVO, DemoDTO.class)), DemoVO.class);
    }

}
