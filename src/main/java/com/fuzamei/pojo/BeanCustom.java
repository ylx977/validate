package com.fuzamei.pojo;

import com.fuzamei.customerize.ListNotHasNull;
import lombok.Data;

import java.util.List;

/**
 * Created by fuzamei on 2018/10/8.
 */
@Data
public class BeanCustom {

    @ListNotHasNull(value = 5,message = "book中不能含有null值")
    private List<String> book;

}
