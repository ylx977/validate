package com.fuzamei.service;

import com.fuzamei.pojo.OrderPO;

/**
 * Created by ylx on 2018/12/13.
 */
public interface CacheService {

    OrderPO insert(OrderPO orderPO);

    OrderPO find(OrderPO orderPO);

    void update(OrderPO orderPO);

    void delete(String id);
}
