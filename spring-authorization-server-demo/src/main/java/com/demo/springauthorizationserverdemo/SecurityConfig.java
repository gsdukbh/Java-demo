package com.demo.springauthorizationserverdemo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;

@Configuration
@Import(OAuth2AuthorizationServerConfiguration.class)
public class SecurityConfig {

}
