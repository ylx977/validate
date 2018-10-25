package com.fuzamei.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fuzamei on 2018/10/24.
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world version 4.4";
    }

    @RequestMapping("/hello2")
    public String hello2(){
        return "hello world 3";
    }

}
