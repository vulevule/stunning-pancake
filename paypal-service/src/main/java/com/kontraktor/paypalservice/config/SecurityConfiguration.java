package com.kontraktor.paypalservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
          http.cors().and()
          .csrf().disable()
          
              .authorizeRequests()
              .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
              .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
              
              
              
              
              
    }
}