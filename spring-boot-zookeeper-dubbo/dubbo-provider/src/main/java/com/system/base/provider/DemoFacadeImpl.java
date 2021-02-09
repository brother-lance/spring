package com.system.base.provider;

import com.system.base.api.dto.DemoDTO;
import com.system.base.api.facade.DemoFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

@Slf4j
@Service(owner = "lance.wu", version = "1.0.0", group = "demoGroup")
public class DemoFacadeImpl implements DemoFacade {

    @Override
    public DemoDTO call(String logId, DemoDTO demoDTO) {
        Thread.currentThread().setName(logId);
        log.info("接送参数：{}", demoDTO);
        return demoDTO;
    }
}
