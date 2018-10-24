package com.fuzamei.pojo;

import com.fuzamei.validategroupd.First;
import com.fuzamei.validategroupd.Second;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by fuzamei on 2018/10/8.
 */
@Data
public class BeanBO2 {

    @NotEmpty(message = "id不能为空值")
    @NotNull(message = "id不能为null")
    @Min(value = 0L,message = "id不能小于0",groups = {First.class})
    private Integer id;

    @NotEmpty(message = "username不能为空",groups = {Second.class})
    private String username;

    @NotEmpty(message = "password不能为空",groups = {Second.class})
    private String password;

    @NotEmpty(message = "name不能为空值")
    @NotNull(message = "name不能为null",groups = {First.class})
    private String name;

    @NotEmpty(message = "other不能为空")
    private String other;

}
