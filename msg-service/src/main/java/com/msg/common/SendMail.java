package com.msg.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SendMail {

    @Value("${spring.mail.username}")
    private String from;

    //引入邮件接口
    @Resource
    private JavaMailSender mailSender;

    public  void sendEmail(String toEmail, String subject, String content){
        //创建邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        //设置发件人信息
        message.setFrom(from);
        //发给谁
        message.setTo(toEmail);
        message.setText(content);
        mailSender.send(message);
    }
}
