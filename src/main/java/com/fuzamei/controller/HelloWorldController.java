package com.fuzamei.controller;

import com.alibaba.fastjson.JSON;
import com.fuzamei.pojo.BeanBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fuzamei on 2018/10/24.
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world version 4.4 from docker container of "+port+" port v 3.4";
    }

    @RequestMapping("/baidu")
    public String baidu(){
        String forObject = restTemplate.getForObject("https://www.baidu.com", String.class);
        return forObject;
    }

    @RequestMapping("/hello2")
    public String hello2(){
        return "hello world 3";
    }

    @RequestMapping("/hellojson")
    public String hellojson(){
        BeanBO beanBO = new BeanBO();
        beanBO.setAddress("address");
        beanBO.setEmail("email");
        beanBO.setLength("123");
        beanBO.setM1(1);
        beanBO.setM2(2);
        return JSON.toJSONString(beanBO);
    }

    @CrossOrigin
    @RequestMapping("/helloInterceptor")
    public String helloInterceptor(@RequestBody BeanBO beanBO){
        return JSON.toJSONString(beanBO);
    }

    @CrossOrigin
    @RequestMapping("/helloInterceptor2")
    public String helloInterceptor2(){
        int i = 1/0;
        return "helloInterceptor2";
    }

    public static void main(String[] args) {
        BeanBO beanBO = new BeanBO();
        beanBO.setM1(111);
        beanBO.setM2(222);
        String s = JSON.toJSONString(beanBO);
        System.out.println(s);
    }



}
