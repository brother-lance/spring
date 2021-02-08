package com.system.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBaseTest {

    protected String baseUrlString = "http://localhost:8080/api/v1.0/";

    @Before
    public void setUp() {
        System.out.println("请求地址：" + this.baseUrlString);
    }
}
