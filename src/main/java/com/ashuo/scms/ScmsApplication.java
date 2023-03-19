package com.ashuo.scms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class ScmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScmsApplication.class, args);
        System.out.println("项目启动成功。。。");
    }
}
