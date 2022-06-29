package top.werls.springbootdemo;

import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author leejiawei
 */
@SpringBootApplication
@RestController
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }


    @GetMapping("/")
    public String helleWorld(){
        RestTemplate template= new RestTemplate();
        return "<h2> hello world! <h2/> ";
    }
}
