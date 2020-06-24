package com.mydubbo.dubbo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisPubSubCommands;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


@SpringBootTest
class DubboproviderApplicationTests {
    @Resource
    RedisTemplate<String,Object> redisTemplate;
    @Resource
    RedisConnection redisConnection;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("dasd","dasdsa");
        System.out.println(redisTemplate.opsForValue().get("dasd"));

    }
}
