package com.jenkin.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: bigData-zj
 * @date: 2023/6/28 9:45
 * @功能描述: spring-boot启动类
 * @version: 1.0
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 固定写法
        SpringApplication.run(MainApplication.class, args);
    }
}