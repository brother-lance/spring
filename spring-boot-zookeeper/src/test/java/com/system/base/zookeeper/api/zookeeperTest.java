package com.system.base.zookeeper.api;

import com.system.base.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class zookeeperTest {

    @Autowired
    ZkApi zkApi;

    public void test(){
        String path = "/zk-watcher-2";
        log.info("【执行初始化测试方法。。。。。。。。。。。。】");
        zkApi.createNode(path, "测试");
        String value = zkApi.getData(path, new WatcherApi());
        log.info("【执行初始化测试方法getData返回值。。。。。。。。。。。。】={}", value);

        // 删除节点出发 监听事件
        //deleteNode(path);
    }

}
