package com.ytzl.gotrip;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.ytzl.gotrip.mapper.GotripUserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

/**
 * 用户模块提供者
 */
@SpringBootApplication
@MapperScan(basePackageClasses = GotripUserMapper.class)
@EnableDubboConfiguration
public class UserProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApp.class,args);
    }
}
