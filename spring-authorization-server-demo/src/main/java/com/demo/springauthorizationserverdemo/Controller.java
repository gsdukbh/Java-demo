package com.demo.springauthorizationserverdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping("/")
    public String name() {
        return "hello word";
    }
}
