package com.lbw.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author by lbw , Date on 2018/10/11.
 */
@Data
@ConfigurationProperties(prefix = "lbw.security")
public class SecurityProperties {

  // 浏览器配置
  private BrowserProperties browser = new BrowserProperties();

  // 验证码配置
  private ValidateCodeProperties code = new ValidateCodeProperties();
}
