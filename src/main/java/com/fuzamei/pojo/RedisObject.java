package com.fuzamei.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ylx on 2018/12/4.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RedisObject implements Serializable{

    private Integer intNum;
    private Long longNum;
    private String name;
    private Integer age;
    private Integer[] intNums;
    private Long[] longNums;
    private Date currentDate;
    private RedisObject subObj;
    private RedisObject[] subObjs;

}
