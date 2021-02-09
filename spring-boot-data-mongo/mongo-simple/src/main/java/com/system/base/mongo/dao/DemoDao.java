package com.system.base.mongo.dao;

import com.system.base.mongo.entity.DemoEntity;

public interface DemoDao {

    void saveDemo(DemoEntity demoEntity);

    void removeDemo(Long id);

    void updateDemo(DemoEntity demoEntity);

    DemoEntity findDemoById(Long id);
}
