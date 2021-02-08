package com.system.base.provider;

import com.system.base.api.dto.DemoDTO;
import com.system.base.api.facade.DemoFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoFacadeImpl implements DemoFacade {

    @Override
    public void call(String logId, DemoDTO demoDTO) {
        Thread.currentThread().setName(logId);
        log.info("传送参数：{}", demoDTO);
    }
}
