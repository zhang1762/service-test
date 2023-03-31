package com.user.enums;

import lombok.Getter;

@Getter
public enum ValidEnum {
    INVALID(0,"无效"),
    VALID(1,"有效");
    private int status;
    private String desc;

    ValidEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
