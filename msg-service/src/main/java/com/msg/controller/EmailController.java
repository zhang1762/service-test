package com.msg.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *后续邮件可以单独拆分为一个单独的公共服务
 */
@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

}
