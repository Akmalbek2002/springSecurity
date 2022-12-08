package com.example.springSecurity.xavfsizlik_sozlamalari;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Xavfsizlik_Sozlamalari extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST,"/users/add/**") .hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/users/oqish/**") .hasAnyRole("ADMIN","SUPERUSER")
//                .antMatchers(HttpMethod.GET,"/users/oqishid/*") .hasAnyRole("ADMIN","SUPERUSER","USER")
//                .antMatchers(HttpMethod.PUT,"/users/tahrirlash/*") .hasAnyRole("ADMIN","SUPERUSER")
//                .antMatchers(HttpMethod.DELETE,"/users/ochirish/*") .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Admin") .password(passwordEncoder().encode("12345678"))
                .roles("ADMIN")
                .authorities("ADDUSER","READUSER","EDITUSER","DELETEUSER","IDREAD")
                .and()
                .withUser("superUser") .password(passwordEncoder().encode("4321"))
                .roles("SUPERUSER")
                .authorities("EDITUSER","READUSER")
                .and()
                .withUser("User") .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .authorities("READUSER");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
