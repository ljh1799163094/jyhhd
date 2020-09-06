package com.example.jyhhd;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JyhhdApplication {

    public static void main(String[] args) {
        SpringApplication.run(JyhhdApplication.class, args);
    }

}
