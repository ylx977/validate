package com.fuzamei.service.impl;

import com.fuzamei.annotation.AnalysisActuator;
import com.fuzamei.mapper.CacheMapper;
import com.fuzamei.pojo.OrderPO;
import com.fuzamei.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.UUID;

/**
 * Created by ylx on 2018/12/13.
 */
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheMapper cacheMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CachePut(value="OrderCache",key="#orderPO.getId()",condition = "#orderPO.getId().endsWith('e')")//condition表示当id尾部是e结尾的才会插入缓存
    @CachePut(value="OrderCache",key="#orderPO.getId()")
    public OrderPO insert(OrderPO orderPO) {
        long currentTimeMillis = System.currentTimeMillis();
        orderPO.setCtime(currentTimeMillis);
        orderPO.setUtime(currentTimeMillis);
        int success = cacheMapper.insert(orderPO);
        if(success == 0){
            log.info("插入数据失败");
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //如果返回值那你还是会缓存，应该抛出异常，就不会缓存进去了
            throw new RuntimeException();
        }
        return orderPO;
    }

    @Override
    @Cacheable(value="OrderCache",key="#orderPO.getId()")
    public OrderPO find(OrderPO orderPO) {
        log.info("进入数据库查询的方法了");
        return cacheMapper.find(orderPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "OrderCache",key = "#orderPO.getId()",beforeInvocation = false)
    //如果beforeInvocation是true，表示方法被调用之前就吧缓存清楚，就算方法内部报错，还是会清楚
    // 默认是false，也就是在方法调用之后清除的,如果方法内部报错，就不会删除缓存
    public void update(OrderPO orderPO) {
        cacheMapper.update(orderPO);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @CacheEvict(value = "OrderCache", key = "#p0")
    @AnalysisActuator(note = "这个是一个note")
    public void delete(String id) {
        try {
            log.info("delete的service开始");
            cacheMapper.delete(id);
            log.info("delete的service结束");
            return;
        }finally {
            log.info("delete的finally");
        }
    }
}
