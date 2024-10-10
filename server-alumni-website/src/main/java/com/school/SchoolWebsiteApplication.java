package com.school;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.school.mapper")
@SpringBootApplication
public class SchoolWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolWebsiteApplication.class, args);
    }

}
