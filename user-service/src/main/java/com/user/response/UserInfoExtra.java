package com.user.response;

import com.user.domain.UserInfo;
import lombok.Data;

@Data
public class UserInfoExtra extends UserInfo {
    private String emailContext;
}
