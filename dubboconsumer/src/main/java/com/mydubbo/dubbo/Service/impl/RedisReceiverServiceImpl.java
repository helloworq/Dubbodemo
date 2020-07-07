package com.mydubbo.dubbo.Service.impl;

import com.mydubbo.dubbo.Service.RedisReceiverService;
import org.springframework.stereotype.Component;

@Component
public class RedisReceiverServiceImpl implements RedisReceiverService {
    @Override
    public String receiveMessage(String Message) {
        System.out.println("获取到消息："+Message);
        return "获取到消息："+Message;
    }
}