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

  @Autowired
  private SuccessHandler successHandler;

  @Autowired
  private FailureHandler failureHandler;

  //  加盐加密 防止破解密码相同的账户
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.httpBasic()
    http.formLogin()
//        自定义登陆页面
//        .loginPage("/signIn.html")


//        由请求来确定返回自定义页面
        .loginPage("/authentication/require")
//        默认是/login
        .loginProcessingUrl("/authentication/form")
//        自定义登陆成功处理 实现AuthenticationSuccessHandler
        .successHandler(successHandler)
//        自定义登陆失败处理 实现AUthenticationFailureHandler
        .failureHandler(failureHandler)
        .and()
        .authorizeRequests()
        .antMatchers("/signIn.html","/authentication/require","/authentication/signln.html",securityProperties.getBrowser().getLoginPage()).permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable();


  }
}
