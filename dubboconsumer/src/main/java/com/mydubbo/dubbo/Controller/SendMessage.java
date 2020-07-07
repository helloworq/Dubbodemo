package com.mydubbo.dubbo.Controller;


import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.mydubbo.dubbo.Service.RedisMessageSenderService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//定时器
@Slf4j
@Controller
public class SendMessage {
    @Reference
    RedisMessageSenderService redisMessageSenderService;

    /**
     * 向redis消息队列index通道发布消息
     */
    @ResponseBody
    @RequestMapping("/sendMsg")
    public String sendSubMessage() throws Exception {
        log.warn("============开始远程调用================");
        System.out.println("开始远程调用");
        return redisMessageSenderService.sendSubMessage();
    }
}