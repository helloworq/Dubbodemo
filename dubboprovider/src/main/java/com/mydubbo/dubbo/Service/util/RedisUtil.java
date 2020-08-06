package com.mydubbo.dubbo.Service.util;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 自己封装一个redis的操作库，序列化有点麻烦
 */

public class RedisUtil {
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    public void redisStringUtil(){
        ArrayList<String> list=new ArrayList<>();
        list.add("wdad");
        list.add("dsadasd");
        list.add("dsadasd");
        list.add("dsadasd");
        list.add("dsadasd");
        redisTemplate.opsForList().leftPush("list",list);
        System.out.println(redisTemplate.opsForList().leftPop("list"));
    }
}
