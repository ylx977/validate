package com.fuzamei.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * Created by fuzamei on 2018/10/8.
 */
@Data
public class BeanBO {

    @NotEmpty(message = "name字符串不能为空")
    private String name;

    @NotBlank(message = "address不能为blank")
    private String address;

    @NotNull(message = "v1不能为null")
    private String v1;

    @NotNull(message = "v2不能为null")
    @DecimalMax(message = "v2最大值不能超过20.9",value = "20.9")
    private Integer v2;

    @NotNull(message = "v3不能为null")
    @DecimalMin(message = "v3最小值不能小于10.9",value = "10.9")
    private Integer v3;

    @NotNull(message = "m1不能为null")
    @Max(message = "m1不能超过19这个值",value = 19L)
    private Integer m1;
    @NotNull(message = "m2不能为null")
    @Min(message = "m2不能小于11这个值",value = 11L)
    private Integer m2;

    @NotBlank(message = "pattern不能为blank")
    @Pattern(regexp = "^\\w{2,5}$",message = "pattern长度只能在2-5之间")
    private String pattern;

    @Email(message = "必须要是邮箱格式")
    private String email;

    @NotNull(message = "size不能为blank")
    @Range(min = 20,max = 50,message = "区间必须在20-50之间")
    private Integer size;

    @NotBlank(message = "url不能为空")
    @URL(protocol = "http",port = 8080,message = "协议要是http,端口号要是8080")
    private String url;

    @Length(min = 3,max = 6,message = "字符串长度要在3-6位之间")
    private String length;
}
