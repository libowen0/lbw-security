package com.lbw;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbw.properties.LoginType;
import com.lbw.properties.SecurityProperties;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Author by lbw , Date on 2018/10/12.
 */

// 登陆成功后调用
@Slf4j
@Component
public class SuccessHandler extends
    SavedRequestAwareAuthenticationSuccessHandler {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  SecurityProperties securityProperties;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Authentication authentication)
      throws IOException, ServletException {
    log.info("登陆成功");

    if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
      httpServletResponse.setContentType("application/json;charset=UTF-8");
      httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
    } else {
      super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }
  }
}
