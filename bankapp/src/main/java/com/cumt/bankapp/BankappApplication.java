package com.cumt.bankapp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.cumt.bankapp.mapper")
@SpringBootApplication

public class BankappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankappApplication.class, args);
    }

}
