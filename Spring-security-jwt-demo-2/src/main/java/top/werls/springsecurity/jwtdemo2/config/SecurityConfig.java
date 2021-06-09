package top.werls.springsecurity.jwtdemo2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.werls.springsecurity.jwtdemo2.security.JwtAuthenticationTokenFilter;
import top.werls.springsecurity.jwtdemo2.security.RestAuthenticationEntryPoint;
import top.werls.springsecurity.jwtdemo2.security.RestfulAccessDeniedHandler;
import top.werls.springsecurity.jwtdemo2.service.DemoUserDetailsServiceImpl;

/**
 * @author leejiawei
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private DemoUserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     * <p>
     * Any endpoint that requires defense against common vulnerabilities can be specified
     * here, including public ones. See {@link HttpSecurity#authorizeRequests} and the
     * `permitAll()` authorization rule for more details on public endpoints.
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement()// 基于token，所以不需要session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               ;
    }
}
