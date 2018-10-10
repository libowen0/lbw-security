package com.lbw.config;

import com.lbw.filter.TimeFilter;
import com.lbw.intercepter.TimeIntercepter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author by lbw , Date on 2018/10/10.
 */
// 将第三方过滤器加到过滤器链上
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  TimeIntercepter timeIntercepter;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(timeIntercepter);
  }

  @Bean
  public FilterRegistrationBean timeFilter(){
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    TimeFilter timeFilter = new TimeFilter();
    registrationBean.setFilter(timeFilter);
//    满足这些路径的时候过滤器才起作用
    List<String> urls = new ArrayList<>();
    urls.add("/*");
    registrationBean.setUrlPatterns(urls);
    return registrationBean;
  }
}


// 请求按照以下顺序来进行访问
// 1.filter
// 2.intercepter
// 3.ControllerAdvice
// 4.aspect
// 5.controller
