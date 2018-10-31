package com.lbw.code;

import com.lbw.validate.code.ImageCode;
import com.lbw.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Author by lbw , Date on 2018/10/17.
 */

// 以增量的模式来应对变化
//  实现validateCodeGenerator类 并注入imageCodeGenerator bean
//  就替换了之前旧逻辑
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

  @Override
  public ImageCode generate(ServletWebRequest request) {
    System.out.println("更高级的图形验证码生成器");
    return null;
  }
}
