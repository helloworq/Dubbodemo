package com.mydubbo.dubbo.Service.impl;

import com.mydubbo.dubbo.Service.RedisReceiverService;
import com.mydubbo.dubbo.Service.SayhelloService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisReceiverServiceImpl implements RedisReceiverService {
    @Autowired
    SayhelloService sayhelloService;
    @Override
    public String receiveMessage(String Message) {
        System.out.println("获取到消息："+Message);
        return "获取到消息："+Message;
    }
}
