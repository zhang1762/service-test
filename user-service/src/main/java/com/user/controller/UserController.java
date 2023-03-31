package com.user.controller;

import com.common.PageDateInfo;
import com.common.Response;
import com.user.domain.UserInfo;
import com.user.req.UserInfoReq;
import com.user.req.UserPageReq;
import com.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增用户接口
     * @param userInfoReq
     * @return
     */
    @PostMapping("/user/add")
    public Response addUser(@RequestBody @Validated UserInfoReq userInfoReq) {
        userService.addUser(userInfoReq);
        return Response.buildSuccess();
    }

    /**
     * 根据id查询某个用户的具体信息
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Response getUser(@PathVariable Long id) {
        UserInfo userInfo = userService.getUserById(id);
        return Response.buildSuccess(userInfo);
    }

    /**
     * 修改具体某个用户
     * @param id
     * @param userInfoReq
     * @return
     */
    @PostMapping("/user/update/{id}")
    public Response updateUser(@PathVariable Long id,
                                   @RequestBody @Validated UserInfoReq userInfoReq ) {
        userService.updateUser(id, userInfoReq);
        return Response.buildSuccess();
    }

    /**
     * 将用户失效(支持批量)
     * @param ids
     * @return
     */
    @PostMapping("/user/invalid")
    public Response invalidUserByIds(@RequestBody List<Long> ids) {
        userService.invalidUserByIds(ids);
        return Response.buildSuccess();
    }

    /**
     * 用户列表（返回包含已发送该用户的注册邮件内容）
     * @param userPageReq
     * @return
     */
    @PostMapping("/user/list")
    public Response listUsers(@RequestBody @Validated UserPageReq userPageReq) {
        PageDateInfo dateInfo = userService.listUsers(userPageReq);
        return Response.buildSuccess(dateInfo);
    }
}
