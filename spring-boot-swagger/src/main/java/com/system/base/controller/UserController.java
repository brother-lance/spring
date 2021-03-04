package com.system.base.controller;

import com.system.base.entity.User;
import com.system.base.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author wushengbin
 * @Date 2021/3/4 18:51
 **/
@RestController
@RequestMapping("swagger")
@Api(tags = "用户crud测试")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Swagger测试
     */
    @PostMapping("/testSwagger")
    @ApiOperation("Swagger的测试")
    public User getUserById(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return user;
    }
}
