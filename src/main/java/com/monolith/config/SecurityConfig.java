package com.monolith.config;

import com.monolith.security.CustomAuthenticationProvider;
import com.monolith.security.MyBasicAuthenticationEntryPoint;
import com.monolith.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DefaultUserService userService;

    @Autowired
    private MyBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/*").authenticated()
                .antMatchers("/account/**").authenticated()
                .antMatchers("/users").authenticated()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/create/user").permitAll()
                .antMatchers("/user").authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .authenticationEntryPoint(basicAuthenticationEntryPoint)
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider);
    }

}
