package com.lbw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Author by lbw , Date on 2018/10/11.
 */

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  SecurityProperties securityProperties;

  //  加盐加密 防止破解密码相同的账户
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.httpBasic()
    http.formLogin()
//        .loginPage("/signIn.html")
//        由请求来确定返回自定义页面
        .loginPage("/authentication/require")
//        默认是/login
        .loginProcessingUrl("/authentication/form")
        .and()
        .authorizeRequests()
        .antMatchers("/signIn.html","/authentication/require","/authentication/signln.html",securityProperties.getBrowser().getLoginPage()).permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable();

//    api安全方式
//    1. 访问api FilterSecurityInterceptor 拦截未认证，抛出异常
//    2. ExceptionTranslationFilter 捕获异常 并跳转到/login
//    3. 正确填写表单，由UsernamePasswordAuthenticationFiler来进行认证
//    4. 认证通过，调用FilterSecurityInterceptor来放行api
//    5. 访问controller方法
  }
}
