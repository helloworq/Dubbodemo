package com.mydubbo.dubbo;


import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@EnableDubbo(scanBasePackages = "com.mydubbo.dubbo")
@PropertySource("classpath:/application.properties")
@SpringBootApplication
public class DubboproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboproviderApplication.class, args);
    }

}
