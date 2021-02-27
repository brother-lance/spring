package com.system.base.dal.mapper;

import com.system.base.dal.entity.CustomerBaseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerBaseInfoMapper {

    int insert(CustomerBaseInfo record);

    List<CustomerBaseInfo> selectAll();
}