package com.msg.common;

import com.BaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

class SendMailTest extends BaseTest {
    @Resource
    private SendMail sendMail;

    @Test
    void sendEmail() {
        sendMail.sendEmail("397002119@qq.com","test","test");
    }
}