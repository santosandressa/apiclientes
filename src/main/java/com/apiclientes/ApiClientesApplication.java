package com.apiclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiClientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiClientesApplication.class, args);
    }

}
