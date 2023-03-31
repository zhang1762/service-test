package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@MapperScan(basePackages = {"com.msg.dao","com.user.dao"})
public class ServiceApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
