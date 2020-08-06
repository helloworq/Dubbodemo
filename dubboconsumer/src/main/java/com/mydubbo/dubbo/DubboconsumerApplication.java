package com.mydubbo.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

@EnableCaching
@EnableDubbo(scanBasePackages = "com.mydubbo.dubbo")
@PropertySource("classpath:/application.properties")
@SpringBootApplication
public class DubboconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboconsumerApplication.class, args);
    }

}
