package com.system.base.api.facade;


import com.system.base.api.dto.DemoDTO;

public interface DemoFacade {

    DemoDTO call(String logId, DemoDTO demoDTO);
}
