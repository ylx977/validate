package com.fuzamei.mapper;

import com.fuzamei.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ylx
 * Created by fuzamei on 2018/11/15.
 */
@Mapper
public interface BigInfoMapper {


    int insertUsers(List<UserPO> list);
}
