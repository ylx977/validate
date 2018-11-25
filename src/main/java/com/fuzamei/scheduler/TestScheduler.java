package com.fuzamei.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ylx on 2018/11/25.
 */
@Component
@Slf4j
public class TestScheduler {

    @Scheduled(cron = "0 0 16 * * ?", zone = "GMT+8")
    public void test01(){
        System.out.println("设置东八区的时区的定时器");
        System.out.println("应该在东八区下午4点启动的");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println("当前时间(没有手动设置时区)："+format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        String format2 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+0)："+format2);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String format3 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+2)："+format3);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+4"));
        String format4 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+4)："+format4);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+6"));
        String format5 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+6)："+format5);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String format6 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+8)："+format6);
    }

    /**
     * 指定下午16点启动
     */
    @Scheduled(cron = "0 0 16 * * ?")
    public void test02(){
        System.out.println("没有设置东八区的时区的定时器");
        System.out.println("应该在东八区晚上12点启动的");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println("当前时间(没有手动设置时区)："+format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        String format2 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+0)："+format2);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String format3 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+2)："+format3);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+4"));
        String format4 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+4)："+format4);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+6"));
        String format5 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+6)："+format5);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String format6 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+8)："+format6);
    }
    /**
     * 指定上午9点启动
     */
    @Scheduled(cron = "0 0 9 * * ?")
    public void test04(){
        System.out.println("没有设置东八区的时区的定时器");
        System.out.println("应该在东八区下午17点启动的");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println("当前时间(没有手动设置时区)："+format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        String format2 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+0)："+format2);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String format3 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+2)："+format3);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+4"));
        String format4 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+4)："+format4);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+6"));
        String format5 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+6)："+format5);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String format6 = simpleDateFormat.format(new Date());
        System.out.println("当前时间(设置时区+8)："+format6);
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void test03(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format+":检测机制--->确保可以正常使用");
    }

}
