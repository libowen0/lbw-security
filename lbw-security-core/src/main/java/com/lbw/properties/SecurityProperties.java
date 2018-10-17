package com.lbw.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author by lbw , Date on 2018/10/11.
 */
@Data
@ConfigurationProperties(prefix = "lbw.security")
public class SecurityProperties {

  private BrowserProperties browser = new BrowserProperties();

  private ValidateCodeProperties code = new ValidateCodeProperties();
}
