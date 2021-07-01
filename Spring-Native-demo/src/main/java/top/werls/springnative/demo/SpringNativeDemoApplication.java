package top.werls.springnative.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringNativeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringNativeDemoApplication.class, args);
    }


    @RequestMapping("/")
    public String index(){
        return  "hello word ";
    }
}
