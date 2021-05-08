package top.werls.springbootvalidfromdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.validation.Valid;

@SpringBootApplication
@RestController
public class SpringbootValidFromDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootValidFromDemoApplication.class, args);
    }


    @PostMapping("/")
    public Book post(@Valid Book book)  {
        return book;
    }
    @PostMapping("/post")
    public String post2(@Validated Book book){
        return null;
    }
}
