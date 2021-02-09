package com.system.base.redis;

import com.system.base.ClusterRedisApplication;
import com.system.base.redis.component.RedisComponent;
import com.system.base.redis.entity.RedisDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClusterRedisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {

    @Autowired
    RedisComponent redisComponent;

    @Test
    public void testSet() {
        String key = "_KEY-005";
        RedisDO redisDO = new RedisDO();
        redisDO.setId(key);
        redisDO.setName("测试名称");
        redisDO.setAge(18);
        redisDO.setStatus("normal");
        // 存储
        redisComponent.set(key, redisDO);

        // 设置时间
        redisComponent.expire(key, 10);

        String key2 = "_KEY-006";
        redisComponent.set(key2, redisDO, 10);

    }



}
