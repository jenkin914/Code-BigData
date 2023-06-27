package com.jenkin.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author:jenkin
 * @Date:2023/6/27 23:23
 * 主程序类
 * @SpringBootApplication： 这是一个spring-boot应用
 */

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 固定写法
        SpringApplication.run(MainApplication.class, args);
    }
}
