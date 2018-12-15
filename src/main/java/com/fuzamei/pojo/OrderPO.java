package com.fuzamei.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ylx
 * Created by ylx on 2018/12/13.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderPO implements Serializable{

    private String id;
    private String no;
    private Long ctime;
    private Long utime;

}
