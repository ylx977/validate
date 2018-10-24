package com.fuzamei.pojo.beanBO3;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by fuzamei on 2018/10/8.
 */
@Data
public class BeanBO3 {

    @NotEmpty(message = "name不能为空",groups = {One.class})
    private String name;
    @NotNull(message = "age不能为null",groups = {Two.class})
    @Range(min = 0,max = 150,message = "年龄必须在0-150之间",groups = {Two.class})
    private Integer age;
    @NotEmpty(message = "password不能为空",groups = {Three.class})
    private String password;
    @NotEmpty(message = "username不能为空",groups = {Four.class})
    private String username;
    @NotEmpty(message = "address不能为空",groups = {Five.class})
    private String address;

}

interface One{}
interface Two{}
interface Three{}
interface Four{}
interface Five{}