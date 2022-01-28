package top.werls.springbootjwt.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leejiawei
 * @version TODO
 * @since on  2022/1/28
 */
@RestController
public class helloController {

    @RequestMapping("/")
    public String hello(Authentication authentication) {
        return "hello world " + authentication.getName();
    }
}
