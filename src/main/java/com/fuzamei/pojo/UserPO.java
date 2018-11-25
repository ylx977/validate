package com.fuzamei.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ylx
 * Created by fuzamei on 2018/11/15.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

    private Long id;
    private String username;
    private String password;
    private Integer age;
    private Boolean sex;
    private Double money;
    private String occupation;
    private String personName;
    private Long ctime;
    private Long utime;
}
