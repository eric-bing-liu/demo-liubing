package com.sinosoft.demospringboot2highgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class DemoSpringboot2HighgoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringboot2HighgoApplication.class, args);
    }
}
