package com.ytzl.gotrip;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDubboConfiguration
@EnableAspectJAutoProxy
public class UserConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApp.class,args);
    }
}
