package com.jenkin.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:jenkin
 * @Date:2023/6/27 23:26
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String handle01(){
        return "hello, spring-boot 2!";
    }
}
