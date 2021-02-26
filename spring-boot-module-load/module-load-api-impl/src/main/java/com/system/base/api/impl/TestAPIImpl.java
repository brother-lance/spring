package com.system.base.api.impl;

import com.system.base.api.TestAPI;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author wushengbin
 * @Date 2021/2/22 17:26
 **/
@Service
public class TestAPIImpl implements TestAPI {

    @Override
    public String test() {
        return this.test("");
    }

    @Override
    public String test(String name) {
        return name + "hello";
    }
}
