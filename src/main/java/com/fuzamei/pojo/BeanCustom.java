package com.fuzamei.pojo;

import com.fuzamei.customerize.ListNotHasNull;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by fuzamei on 2018/10/8.
 */
@Data
public class BeanCustom {

    @NotNull(message = "点餐详情不能为空")
    @ListNotHasNull(value = 5,message = "book中不能含有null值")
    private List<String> book;

}
