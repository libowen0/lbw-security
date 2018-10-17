package com.lbw.validate.code;

import com.lbw.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author by lbw , Date on 2018/10/17.
 */

@Configuration
public class ValidateCodeBeanConfig {

  @Autowired
  private SecurityProperties securityProperties;

//  没有 imageCodeGenerator 才执行以下逻辑
  @Bean
  @ConditionalOnMissingBean(name = "imageCodeGenerator")
  public ValidateCodeGenerator imageCodeGenerator(){
    ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
    codeGenerator.setSecurityProperties(securityProperties);
    return codeGenerator;
  }

}
