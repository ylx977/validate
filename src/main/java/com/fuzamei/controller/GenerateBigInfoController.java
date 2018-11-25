package com.fuzamei.controller;

import com.fuzamei.service.BigInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fuzamei on 2018/11/15.
 */
@RestController
@RequestMapping("/biginfo")
public class GenerateBigInfoController {

    @Autowired
    private BigInfoService bigInfoService;

    @RequestMapping("/generate")
    public String generateBigInfo(){
        for(int i = 0; i< 20;i++){
            bigInfoService.generateBiginfo();
        }
        return "success";
    }

}
