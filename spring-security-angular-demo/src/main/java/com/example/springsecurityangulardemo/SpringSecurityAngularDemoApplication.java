package com.example.springsecurityangulardemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * spring-security-angular demo
 * @author lee
 */
@SpringBootApplication
@RestController
public class SpringSecurityAngularDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAngularDemoApplication.class, args);
    }


    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<>(8);
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

}
