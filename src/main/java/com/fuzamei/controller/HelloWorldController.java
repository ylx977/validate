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
        return "hello world version 2";
    }

}
