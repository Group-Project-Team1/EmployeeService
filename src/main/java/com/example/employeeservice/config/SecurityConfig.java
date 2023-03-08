package com.example.employeeservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtFilter jwtFilter;

    @Autowired
    public void setJwtFilter(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
//                .antMatchers("/user/test").permitAll()
//                .antMatchers("/user/getAll", "/user/get/*").hasAuthority("read")
//                .antMatchers("/user/create").hasAuthority("write")
//                .antMatchers("/user/update").hasAuthority("update")
//                .antMatchers("/user/delete/*").hasAuthority("delete")
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers("/user/**").hasAuthority("user")
//                .antMatchers("/watchlist/**").hasAuthority("user")
                .anyRequest()
                .authenticated();
    }



}
