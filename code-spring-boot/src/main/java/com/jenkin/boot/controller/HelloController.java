package com.jenkin.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: bigData-zj
 * @date: 2023/6/28 9:44
 * @功能描述: 测试
 * @version: 1.0
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String handle01() {
        return "hello, spring-boot 2!";
    }
}
