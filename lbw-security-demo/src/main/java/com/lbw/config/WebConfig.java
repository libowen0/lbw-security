package com.lbw.config;

import com.lbw.filter.TimeFilter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author by lbw , Date on 2018/10/10.
 */
// 将第三方过滤器加到过滤器链上
@Configuration
public class WebConfig {

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
