package com.user.service;

import com.common.PageDateInfo;
import com.user.domain.UserInfo;
import com.user.req.UserInfoReq;
import com.user.req.UserPageReq;
import com.user.response.UserInfoExtra;

import java.util.List;

public interface UserService {
    void addUser(UserInfoReq userInfoReq);

    UserInfo getUserById(Long id);

    void invalidUserByIds(List<Long> ids);

    void updateUser(Long id, UserInfoReq userInfoReq);

    /**
     * 由于注册和发邮件是两个相对独立的逻辑，后期可将邮件服务拆分；所以列表中包含具体的邮件内容字段时需要单独组装.
     * @param userPageReq
     * @return
     */
    PageDateInfo<UserInfoExtra> listUsers(UserPageReq userPageReq);
}
