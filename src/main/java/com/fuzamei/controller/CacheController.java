package com.fuzamei.controller;

import com.alibaba.fastjson.JSON;
import com.fuzamei.annotation.AnalysisActuator;
import com.fuzamei.pojo.OrderPO;
import com.fuzamei.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.Line;
import java.util.UUID;

/**
 * Created by ylx on 2018/12/13.
 */
@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;


    @RequestMapping("/insert")
    public String insert(@RequestBody OrderPO orderPO){
        orderPO.setId(UUID.randomUUID().toString().replaceAll("-",""));
        try {
            cacheService.insert(orderPO);
        }catch (Exception e){
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/find")
    public String find(@RequestBody OrderPO orderPO) throws Exception{
        try {
            log.info("这是find方法");
            OrderPO order = cacheService.find(orderPO);
            log.info("find方法结束");
            return JSON.toJSONString(order);
        }finally {
            log.info("这是finally中的提示");
        }
    }

    @RequestMapping("/update")
    public String update(@RequestBody OrderPO orderPO){
        try {
            cacheService.update(orderPO);
        }catch (Exception e){
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") String id){
        System.out.println(id);
        try {
            cacheService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

}
