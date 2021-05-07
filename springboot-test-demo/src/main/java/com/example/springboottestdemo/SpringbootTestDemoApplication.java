package com.example.springboottestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SpringbootTestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestDemoApplication.class, args);
    }



    @RequestMapping("/")
    public  @ResponseBody String init(){
        return "hello world";
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public @ResponseBody Book get(){
        return new Book(1,"hello Java","java");
    }

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public @ResponseBody  Book post(  Book book){
        return book;
    }
    @RequestMapping(value = "/post2",method = RequestMethod.POST)
    public @ResponseBody  Book post2( @RequestBody Book book){
        return book;
    }
}
