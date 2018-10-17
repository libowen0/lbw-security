package com.lbw.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * Author by lbw , Date on 2018/10/12.
 */

// AuthenticationException 身份认证过程中抛出异常的基类
public class ValidateCodeException extends AuthenticationException {


  public ValidateCodeException(String msg) {
    super(msg);
  }
}
