package com.calfgz.study.recommend.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    RedisService redisService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void get() {
        String result = (String) redisService.get("test");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void set() {
        boolean result = redisService.set("test", "hello world.");
        Assert.assertTrue(result);
    }
}