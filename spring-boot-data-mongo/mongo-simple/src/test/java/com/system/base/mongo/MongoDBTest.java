package com.system.base.mongo;

import com.system.base.SimpleMongoApplication;
import com.system.base.mongo.dao.DemoDao;
import com.system.base.mongo.entity.DemoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleMongoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoDBTest {

    @Autowired
    DemoDao demoDao;

    @Test
    public void testSave() {
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setDescription("测试描述");
        demoEntity.setTitle("测试主题");
        demoEntity.setBy("用户");
        demoEntity.setUrl("www.cloud.com");
        demoDao.saveDemo(demoEntity);
    }

    @Test
    public void testRemove() {
        long id = 1L;
        demoDao.removeDemo(id);
    }

    @Test
    public void testUpdate() {
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setDescription("测试描述2");
        demoEntity.setTitle("测试主题");
        demoEntity.setBy("用户1");
        demoEntity.setUrl("www.cloud.com");

        demoDao.updateDemo(demoEntity);
    }

    @Test
    public void testFindDemoById() {
        long id = 1L;
        DemoEntity demo = demoDao.findDemoById(id);
        System.out.println(demo);
    }


}
