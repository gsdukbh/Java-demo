package top.werls.springsecurity.jwtdemo2;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.werls.springsecurity.jwtdemo2.commons.Result;
import top.werls.springsecurity.jwtdemo2.entity.DemoUser;
import top.werls.springsecurity.jwtdemo2.service.DemoUserDetailsServiceImpl;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringSecurityJwtDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtDemo2Application.class, args);
    }

    @GetMapping("/public")
    public Result<?> publicGet() {
        return Result.success("hello public");
    }


    @GetMapping("/private")
    public Result<?> privateGet(@AuthenticationPrincipal OAuth2User principal) {

        System.out.println(principal.getAttributes());
        return Result.success(principal);
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;



    @Autowired
    private DemoUserDetailsServiceImpl userDetailsService;

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<?> login(@RequestBody DemoUser umsAdminLoginParam, BindingResult result) {
        String token = userDetailsService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }
}
