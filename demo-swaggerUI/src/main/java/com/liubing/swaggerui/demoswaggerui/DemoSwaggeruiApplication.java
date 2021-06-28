package com.liubing.swaggerui.demoswaggerui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;


@EnableOpenApi
@SpringBootApplication
public class DemoSwaggeruiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSwaggeruiApplication.class, args);
    }

}
