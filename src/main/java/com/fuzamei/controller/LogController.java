package com.fuzamei.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ylx on 2018/12/15.
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {


    @RequestMapping("/testLog")
    public String testLog(){
        log.trace("I am a trace");
        log.debug("I am a debug");
        log.info("I am a info");
        log.warn("I am a warn");
        log.error("I am a error");
        return "success";
    }


}
