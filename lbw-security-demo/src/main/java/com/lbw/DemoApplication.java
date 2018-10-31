package com.lbw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author by lbw , Date on 2018/10/9.
 */

//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})

@EnableJdbcRepositories
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class,args);
  }

  @RequestMapping("/index")
  public String hello(){
    return "hello";
  }
}
