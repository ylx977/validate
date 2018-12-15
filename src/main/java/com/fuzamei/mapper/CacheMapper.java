package com.fuzamei.mapper;

import com.fuzamei.pojo.OrderPO;
import org.apache.ibatis.annotations.*;

/**
 * Created by ylx on 2018/12/13.
 */
@Mapper
public interface CacheMapper {

    @Insert("insert into orders (id, no, ctime, utime) values (#{id}, #{no}, #{ctime}, #{utime})")
    int insert(OrderPO orderPO);

    @Select("select * from orders where id = #{id}")
    OrderPO find(OrderPO orderPO);

    @Update("update orders set no = #{no} where id = #{id}")
    void update(OrderPO orderPO);

    @Delete("delete from orders where id = #{id}")
    void delete(@Param("id") String id);
}
