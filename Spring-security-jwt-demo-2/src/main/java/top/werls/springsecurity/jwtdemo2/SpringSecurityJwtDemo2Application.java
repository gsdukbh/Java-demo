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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.werls.springsecurity.jwtdemo2.commons.BasicResult;
import top.werls.springsecurity.jwtdemo2.entity.BasicUser;
import top.werls.springsecurity.jwtdemo2.service.DemoUserDetailsServiceImpl;

import javax.crypto.SecretKey;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringSecurityJwtDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtDemo2Application.class, args);
    }

    @GetMapping("/public")
    public BasicResult<?> publicGet() {
        return BasicResult.success("hello public");
    }


    @GetMapping("/private")
    public BasicResult<?> privateGet( Principal principal) {


        return BasicResult.success(principal);
    }
    @GetMapping("/")
    public BasicResult<?> oauth2(@AuthenticationPrincipal OAuth2User principal,Principal a) {

        System.out.println(a);

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;
        return BasicResult.success(principal);
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;



    @Autowired
    private DemoUserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BasicResult<?> login(@RequestBody BasicUser umsAdminLoginParam, BindingResult result) {
        String token = userDetailsService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return BasicResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return BasicResult.success(tokenMap);
    }
}
