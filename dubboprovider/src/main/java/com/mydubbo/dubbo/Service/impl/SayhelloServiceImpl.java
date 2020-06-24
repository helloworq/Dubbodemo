package com.mydubbo.dubbo.Service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mydubbo.dubbo.Service.SayhelloService;
import org.springframework.stereotype.Component;

@Service//暴露服务
@Component
public class SayhelloServiceImpl implements SayhelloService {
    @Override
    public String printMsg(String info) {
        return "hello"+info;
    }
}
