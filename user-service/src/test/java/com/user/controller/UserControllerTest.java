package com.user.controller;

import com.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.user.req.UserInfoReq;
import com.user.req.UserPageReq;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseTest {
    private MockMvc mvc;
    @Resource
    private UserController userController;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void before() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
        System.out.println("初始化mock模块");
    }

    @Test
    void addUser() throws Exception {
        before();
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setUserName("张八");
        userInfoReq.setAge(20);
        userInfoReq.setEmail("zhong_yunnan@163.com");

        //序列化
        String json = mapper.writeValueAsString(userInfoReq);
        System.out.println("json = " + json);

        String responseString =
                mvc.perform(
                        MockMvcRequestBuilders.post("/user/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json)
                        )
                        .andExpect(status().isOk())    //返回的状态是200
                        .andDo(print())         //打印出请求和相应的内容
                        .andReturn()
                        .getResponse()
                        .getContentAsString();   //将相应的数据转换为字符串;

        System.out.println("获取结果为：" + responseString);
    }

    @Test
    void getUser() throws Exception{
        before();
        String responseString =
                mvc.perform(
                                MockMvcRequestBuilders.get("/user/7")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())    //返回的状态是200
                        .andDo(print())         //打印出请求和相应的内容
                        .andReturn()
                        .getResponse()
                        .getContentAsString();   //将相应的数据转换为字符串;

        System.out.println("获取结果为：" + responseString);
    }

    @Test
    void updateUser()  throws Exception{
        before();
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setUserName("张刘");
        userInfoReq.setAge(0);
        userInfoReq.setEmail("397002119@qq.com");

        //序列化
        String json = mapper.writeValueAsString(userInfoReq);
        System.out.println("json = " + json);

        String responseString =
                mvc.perform(
                                MockMvcRequestBuilders.post("/user/update/2")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .content(json)
                        )
                        .andExpect(status().isOk())    //返回的状态是200
                        .andDo(print())         //打印出请求和相应的内容
                        .andReturn()
                        .getResponse()
                        .getContentAsString();   //将相应的数据转换为字符串;

        System.out.println("获取结果为：" + responseString);
    }

    @Test
    void invalidUserByIds()  throws Exception{
        before();
        List<Long> userIds = ImmutableList.of(4L);

        //序列化
        String json = mapper.writeValueAsString(userIds);
        System.out.println("json = " + json);

        String responseString =
                mvc.perform(
                                MockMvcRequestBuilders.post("/user/invalid")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .content(json)
                        )
                        .andExpect(status().isOk())    //返回的状态是200
                        .andDo(print())         //打印出请求和相应的内容
                        .andReturn()
                        .getResponse()
                        .getContentAsString();   //将相应的数据转换为字符串;

        System.out.println("获取结果为：" + responseString);
    }

    @Test
    void listUsers()  throws Exception{
        before();
        UserPageReq userPageReq = new UserPageReq();
        //序列化
        String json = mapper.writeValueAsString(userPageReq);
        System.out.println("json = " + json);

        String responseString =
                mvc.perform(
                                MockMvcRequestBuilders.post("/user/list")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .content(json)
                        )
                        .andExpect(status().isOk())    //返回的状态是200
                        .andDo(print())         //打印出请求和相应的内容
                        .andReturn()
                        .getResponse()
                        .getContentAsString();   //将相应的数据转换为字符串;

        System.out.println("获取结果为：" + responseString);
    }
}