package com.lbw;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Author by lbw , Date on 2018/10/11.
 */

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
//    http.httpBasic()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated();
//    Spring security 基于过滤器链，先后依次调用
//    UsernamePasswordAuthenticationFiler 处理表单登陆
//    BasicAuthenticationFilter  处理basic登陆
//    ...
//    ExceptionTranslationFilter 对最后一步抛出异常进行捕获并下一步处理
//    FilterSecurityInterceptor 依据配置来进行放行api

//    api安全方式
//    1. 访问api FilterSecurityInterceptor 拦截未认证，抛出异常
//    2. ExceptionTranslationFilter 捕获异常 并跳转到/login
//    3. 正确填写表单，由UsernamePasswordAuthenticationFiler来进行认证
//    4. 认证通过，调用FilterSecurityInterceptor来放行api
//    5. 访问controller方法
  }
}
