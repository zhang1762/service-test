package com.user.req;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserInfoReq {
    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String userName;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    @Email(message = "邮箱格式错误")
    @NotBlank(message = "邮箱不能为空")
    private String email;
}
