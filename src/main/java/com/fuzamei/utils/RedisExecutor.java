package com.fuzamei.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fuzamei.pojo.OrderPO;
import com.fuzamei.pojo.RedisObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by fuzamei on 2018/7/26.
 */
public class RedisExecutor {

    private static JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(2);
        config.setMinIdle(2);
        config.setTestOnBorrow(true);
        config.setMaxWaitMillis(10000);
        config.setMinIdle(5);

//        pool = new JedisPool(config, "120.55.54.53", 6379, 10000, "contractchain", 1);
        pool = new JedisPool(config, "172.16.100.67", 6379, 10000, "123456", 0);
    }

    private static Jedis jedis = pool.getResource();

    /**
     * 得出一个结论，使用小数据量时，小于3kb左右，JSON格式的数据量是要小于序列化的数据量大小，且反序列化的速度JSON也大于序列化的速度
     *                          当数据量到达100K左右，JSON格式的数据量就超过序列化后的数据量大小了，但是反序列化的速度JSON还是大于序列化的
     *                          当数据量到达1M左右，JSON格式的数据量大于序列化后的数据量大小了，同时反序列化的速度已经超过JSON的反序列化速度了
     * @param args
     */
    public static void main(String[] args) {
        try {
//            List<String> hmget = jedis.hmget("haha", "kk", "kk1", "kk2", "kk3", "kk4");
//            System.out.println(hmget);
//            RedisObject redisObject30 = getObject();
//            RedisObject redisObject30 = getBiggerObject();
//
//            jedis.set("key".getBytes(),RedisUtil.serialize(redisObject30));
//            jedis.set("keH", JSON.toJSONString(redisObject30));
//            long start = System.currentTimeMillis();
//            RedisObject redisObject = (RedisObject) RedisUtil.unserizlize(jedis.get("key".getBytes()));
//            long end = System.currentTimeMillis();
//            System.out.println("耗时"+(end-start));
//
//            long start2 = System.currentTimeMillis();
//            RedisObject redisObject2 = JSON.parseObject(jedis.get("keH"),RedisObject.class);
//            long end2 = System.currentTimeMillis();
//            System.out.println("耗时"+(end2-start2));
//
            String s = jedis.get("OrderCache::ec89c0c3bec143f1a9721c3ffaf17249");
            System.out.println(s);
//            Object object = JSON.parseObject(s, Object.class);
            Object parse = JSONObject.parse(s);
            System.out.println(parse instanceof JSONObject);
            System.out.println(parse);


//            System.out.println(redisObject);
//            System.out.println(redisObject2);


        } catch (Exception e){
            System.out.println("出现异常");
            e.printStackTrace();
        }finally {
            System.out.println("程序结束");
            jedis.close();
            pool.close();
        }
    }




    public static RedisObject getObject(){
        RedisObject subRedisObject = RedisObject.builder()
                .age(2)
                .currentDate(new Date())
                .intNum(10)
                .longNum(20L)
                .longNums(new Long[]{1L})
                .intNums(new Integer[]{1})
                .name("杨凌霄0.1")
                .build();

        RedisObject redisObject1 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄").subObj(subRedisObject).build();
        RedisObject redisObject2 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject1)
                .build();
        RedisObject redisObject3 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject2)
                .build();
        RedisObject redisObject4 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject3)
                .build();
        RedisObject redisObject5 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject4)
                .build();
        RedisObject redisObject6 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject5)
                .build();
        RedisObject redisObject7 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject6)
                .build();
        RedisObject redisObject8 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject7)
                .build();
        RedisObject redisObject9 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject8)
                .build();
        RedisObject redisObject10 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject9)
                .build();
        RedisObject redisObject11 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject10)
                .build();
        RedisObject redisObject12 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject11)
                .build();
        RedisObject redisObject13 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject12)
                .build();
        RedisObject redisObject14 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject13)
                .build();
        RedisObject redisObject15 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject14)
                .build();
        RedisObject redisObject16 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject15)
                .build();
        RedisObject redisObject17 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject16)
                .build();
        RedisObject redisObject18 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject17)
                .build();
        RedisObject redisObject19 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject18)
                .build();
        RedisObject redisObject20 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject19)
                .build();
        RedisObject redisObject21 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject20)
                .build();
        RedisObject redisObject22 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject21)
                .build();
        RedisObject redisObject23 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject22)
                .build();
        RedisObject redisObject24 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject23)
                .build();
        RedisObject redisObject25 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject24)
                .build();
        RedisObject redisObject26 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject25)
                .build();
        RedisObject redisObject27 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject26)
                .build();
        RedisObject redisObject28 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject27)
                .build();
        RedisObject redisObject29 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject28)
                .build();
        RedisObject redisObject30 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject29)
                .build();
        RedisObject redisObject31 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject30)
                .build();
        RedisObject redisObject32 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject31)
                .build();
        RedisObject redisObject33 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject32)
                .build();
        RedisObject redisObject34 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject33)
                .build();
        RedisObject redisObject35 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject34)
                .build();
        RedisObject redisObject36 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject35)
                .build();
        RedisObject redisObject37 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject36)
                .build();
        RedisObject redisObject38 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject37)
                .build();
        RedisObject redisObject39 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject38)
                .build();
        RedisObject redisObject40 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject39)
                .build();
        RedisObject redisObject41 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject40)
                .build();
        RedisObject redisObject42 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject41)
                .build();
        RedisObject redisObject43 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject42)
                .build();
        RedisObject redisObject44 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject43)
                .build();
        RedisObject redisObject45 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject44)
                .build();
        RedisObject redisObject46 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject45)
                .build();
        RedisObject redisObject47 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject46)
                .build();
        RedisObject redisObject48 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject47)
                .build();
        RedisObject redisObject49 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject48)
                .build();
        RedisObject redisObject50 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject49)
                .build();
        RedisObject redisObject51 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject50)
                .build();
        RedisObject redisObject52 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject51)
                .build();
        RedisObject redisObject53 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(redisObject52)
                .build();
        return redisObject53;
    }

    public static RedisObject getBiggerObject(){
        RedisObject redisObject1 = RedisObject.builder()
                .age(20).currentDate(new Date()).intNum(100).longNum(200L).longNums(new Long[]{1L,2L,3L,4L}).intNums(new Integer[]{1,2,3,4}).name("杨凌霄")
                .subObj(getObject())
                .subObjs(new RedisObject[]{getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),
                        getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject(),getObject()})
                .build();
        return redisObject1;
    }







}
