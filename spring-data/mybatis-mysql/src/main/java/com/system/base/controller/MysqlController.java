package com.system.base.controller;

import com.system.base.dal.entity.CustomerBaseInfo;
import com.system.base.dal.mapper.CustomerBaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wushengbin
 * @Date 2021/2/26 19:16
 **/
@RestController
@RequestMapping("test")
public class MysqlController {

    @Autowired
    private CustomerBaseInfoMapper customerBaseInfoMapper;

    // 查询所有
    @GetMapping
    public Object test() {
        return customerBaseInfoMapper.selectAll();
    }

    // 查询所有
    @PostMapping
    public Object test(CustomerBaseInfo baseInfo) {

        baseInfo.setStatus("NORMAL");
        baseInfo.setCreatedAt(new Date());
        baseInfo.setCreatedBy("SYSTEM");
        customerBaseInfoMapper.insert(baseInfo);

        return customerBaseInfoMapper.selectAll();
    }

}
