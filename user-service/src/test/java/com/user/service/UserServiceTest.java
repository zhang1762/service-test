package com.user.service;

import com.BaseTest;
import com.common.PageDateInfo;
import com.google.common.collect.ImmutableList;
import com.user.domain.UserInfo;
import com.user.req.UserInfoReq;
import com.user.req.UserPageReq;
import com.user.response.UserInfoExtra;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

class UserServiceTest extends BaseTest {

    private static final Long ID = 2L;
    @Resource
    private UserService userService;

    @Test
    void addUser() {
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setUserName("张三");
        userInfoReq.setAge(18);
        userInfoReq.setEmail("397002119@qq.com");
        userService.addUser(userInfoReq);
    }

    @Test
    void getUserById() {
        UserInfo userInfo = userService.getUserById(ID);
        assert userInfo != null;
    }

    @Test
    void invalidUserByIds() {
        userService.invalidUserByIds(ImmutableList.of(ID));
    }

    @Test
    void updateUser() {
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setUserName("张四");
        userInfoReq.setAge(19);
        userInfoReq.setEmail("397002119@qq.com");
        userService.updateUser(ID, userInfoReq);
    }

    @Test
    void listUsers() {
        UserPageReq userPageReq = new UserPageReq();
        PageDateInfo<UserInfoExtra> list = userService.listUsers(userPageReq);
        assert list != null;
    }
}