package com.lbw.properties;

import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/17.
 */

@Data
public class ValidateCodeProperties {

  // 图片验证码校验
  private ImageCodeProperties image = new ImageCodeProperties();
}
