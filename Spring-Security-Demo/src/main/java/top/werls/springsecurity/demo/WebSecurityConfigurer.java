package top.werls.springsecurity.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author leejiawei
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    System.out.println(request);
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(200);
                    response.getWriter().write("ok");
                })
                .failureHandler((request, response, authentication)->{
                    System.out.println(request);
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(200);
                    response.getWriter().write("failure ");
                    System.out.println(request);

                })
                .permitAll()
                .and().cors().disable()
                .csrf().disable();
    }
}
