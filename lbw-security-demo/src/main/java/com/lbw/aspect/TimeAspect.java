package com.lbw.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Author by lbw , Date on 2018/10/10.
 */

//
//@Aspect
//@Component
public class TimeAspect {

  @Around("execution(* com.lbw.controller.UserController.*(..))")
  public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {

    System.out.println("time aspect start");

    Object[] args = point.getArgs();
    for(Object arg: args){
      System.out.println("arg is"+arg);
    }

    long start = System.currentTimeMillis();
    Object object = point.proceed();
    System.out.println("time aspect 耗时："+(start -  System.currentTimeMillis()));
    System.out.println("time aspect end");

    return object;
  }
}
