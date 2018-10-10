package com.lbw.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;

/**
 * Author by lbw , Date on 2018/10/10.
 */
//@Component
public class TimeFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("time Filter init");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    System.out.println("time Filter start");

    long start = System.currentTimeMillis();

    filterChain.doFilter(servletRequest, servletResponse);
    System.out.println("time Filter handle" + (System.currentTimeMillis() - start));
    System.out.println("time Filter finsh");
  }

  @Override
  public void destroy() {
    System.out.println("time Filter destory");
  }
}
