package com.mydubbo.dubbo;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@SpringBootTest
class DubboproviderApplicationTests {
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    private final static Logger logger = LoggerFactory.getLogger(DubboproviderApplicationTests.class);

    @Test
    void contextLoads() {
        //redisTemplate.opsForValue().set("dasd","dasdsa");
        //System.out.println(redisTemplate.opsForValue().get("dasd"));
    }


}
