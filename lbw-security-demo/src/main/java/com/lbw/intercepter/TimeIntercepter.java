package com.lbw.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author by lbw , Date on 2018/10/10.
 */

@Component
public class TimeIntercepter implements HandlerInterceptor {

  //  controller 中方法被调用之前
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    System.out.println("preHandle");

    System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
    // 拦截器会拦截所有controller方法 包括系统提供的 BasicErrorController
    System.out.println(((HandlerMethod)handler).getMethod().getName());
    request.setAttribute("startTime", System.currentTimeMillis());
    return true;
  }

  //  controller 中方法被调用之后，且抛出异常不会被调用
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    System.out.println("postHandle");
    Long start = (Long) request.getAttribute("startTime");
    System.out.println("time interception 耗时：" + (System.currentTimeMillis() - start));
  }

  //  最后调用
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    System.out.println("aferCompletion");
    Long start = (Long) request.getAttribute("startTime");
    System.out.println("time interception 耗时：" + (System.currentTimeMillis() - start));
    System.out.println("ex is" + ex);
  }
}
