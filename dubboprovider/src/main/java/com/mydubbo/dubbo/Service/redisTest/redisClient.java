package com.mydubbo.dubbo.Service.redisTest;


import org.springframework.data.redis.core.RedisTemplate;

public class redisClient {
    public void init(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.opsForValue().set("dasd","dasdsa");
        System.out.println(redisTemplate.opsForValue().get("dasd"));

    }
}
