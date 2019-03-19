package com.example.demo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description:
 * @date:2019-3-18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Resource
    private RedisTemplate redisTemplate;


    @Test
    public void setStringTest(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("testKey", "testValue");

    }

    @Test
    public void setStringByRT(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("testKey2", "testValue2");
    }
}
