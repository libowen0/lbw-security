package com.lbw.validate;

import com.lbw.service.HelloService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author by lbw , Date on 2018/10/10.
 */
public class MyConstraintValdate implements ConstraintValidator<MyConstraint,Object> {

  @Autowired
  HelloService helloService;

  @Override
  public void initialize(MyConstraint constraintAnnotation) {
    System.out.println("valid init");
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    System.out.println(o);
    helloService.greeting("tom");
    return false;
  }
}
