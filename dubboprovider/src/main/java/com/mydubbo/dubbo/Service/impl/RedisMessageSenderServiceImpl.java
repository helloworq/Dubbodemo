package com.mydubbo.dubbo.Service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mydubbo.dubbo.Service.RedisMessageSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
@Service
@Component
public class RedisMessageSenderServiceImpl implements RedisMessageSenderService {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    /**
     * 向redis消息队列index通道发布消息
     */
    @Override
    public String sendSubMessage() throws Exception{
        String a="woc";
        stringRedisTemplate.convertAndSend("index","求求你调用成功");
        return "success---"+a;
    }
}