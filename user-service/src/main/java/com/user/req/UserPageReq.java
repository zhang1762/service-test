package com.user.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPageReq {

    @Builder.Default
    private int pageNum = 1;

    @Builder.Default
    @Max(100)
    private int pageSize = 20;
}
