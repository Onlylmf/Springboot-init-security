package com.murphy.config;

import com.murphy.model.entity.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity    // 添加 security 过滤器
public class WebSecurityConfig {


    @Autowired
    UserDetailsService userDetailsService;


    //过滤器链配置
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        Filter filter=new Filter() {

            @Override
            public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
                    throws IOException, ServletException {

                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                try {
                    chain.doFilter(request, response);
                }catch (Exception ex){
                    ExceptionHandlerAdvice.handerError(request, response, ex);//自己处理提出
                }

            }
        };


        http
                .addFilterAfter(filter, ExceptionTranslationFilter.class)
                .exceptionHandling()
                    .accessDeniedHandler((request, response, e) -> ExceptionHandlerAdvice.handerError(request, response, e))
                    .authenticationEntryPoint((request, response, e) -> ExceptionHandlerAdvice.handerError(request, response, e))
                    .and()
                .authorizeRequests()
                .antMatchers("/sysUser/login").permitAll() // 放开权限的url
                .anyRequest().authenticated()
                .and()
                .httpBasic().and()
                .headers().frameOptions().disable().and()
                .csrf().disable();
        return http.build();
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


}
