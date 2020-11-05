package com.example.jyhhd;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.example.jyhhd.mapper")
public class JyhhdApplication {

    public static void main(String[] args) {
        SpringApplication.run(JyhhdApplication.class, args);
    }

}
