package com.gdpu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
@Configuration
@SpringBootApplication
@MapperScan("com.gdpu.mapper")
public class WareHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WareHouseApplication.class, args);
    }
}
