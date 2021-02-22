package com.system.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author wushengbin
 * @Date 2021/2/22 17:31
 **/
@RestController
@RequestMapping("api/v1.0/module/load")
@Deprecated
public class TestController {
//
//    @Autowired(required = false)
//    TestAPI testAPI;

    @GetMapping
    public String test() {
        //return testAPI.test();
        return "1";
    }
}
