package com.example.oauth2clientdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////
////        http
////                .authorizeRequests(a -> a
////                        .antMatchers("/", "/error", "/webjars/**").permitAll()
////                        .anyRequest().authenticated()
////                )
//////                .exceptionHandling(e -> e
//////                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//////                )
//////                .oauth2Login()
//////                .and()
//////                .logout()
//////                .logoutSuccessUrl("/")
//////                .permitAll()
////        ;
////    }
//}
