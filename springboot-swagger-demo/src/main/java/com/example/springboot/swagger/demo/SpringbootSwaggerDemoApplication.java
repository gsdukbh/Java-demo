package com.example.springboot.swagger.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@SpringBootApplication
@Slf4j
public class SpringbootSwaggerDemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringbootSwaggerDemoApplication.class, args);
    }

}
