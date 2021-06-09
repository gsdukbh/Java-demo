package top.werls.springsecurity.jwtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.werls.springsecurity.jwtdemo.util.JwtToken;

import javax.annotation.Resource;

/**
 * @author leejiawei
 */
@SpringBootApplication
@RestController
public class SpringSecurityJwtDemoApplication {

    @Resource
    private JwtToken jwtToken;

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityJwtDemoApplication.class, args);
    }


    @GetMapping("/public")
    public String publicGet() {
        System.out.println(jwtToken.getTokenHead());

        return "hello public";
    }


    @GetMapping("/private")
    public String privateGet() {

        return "hello Private";
    }

}
