package top.werls.springsecurity.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leejiawei
 */
@SpringBootApplication
@RestController
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }

    @GetMapping("/")
    public String getIndex(Authentication authentication) {
        System.out.println(authentication.getName());
        return authentication.toString();
    }
}
