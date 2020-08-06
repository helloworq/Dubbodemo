package com.mydubbo.dubbo.prev;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "msg")
public class UserContainer {
    User user=new User();
}
