package com.example.demo.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		// H2コンソール見れるように設定
    		 http.csrf().disable();
       http.headers().frameOptions().disable();
       http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/ranking").hasAuthority("SCOPE_todo:read")
                .mvcMatchers(HttpMethod.POST, "/poker_money").hasAuthority("SCOPE_todo:write");
       http.oauth2ResourceServer()
                .jwt();
    }
}