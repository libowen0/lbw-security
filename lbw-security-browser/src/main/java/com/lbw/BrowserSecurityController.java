package com.lbw;

import com.lbw.support.SimpleResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author by lbw , Date on 2018/10/11.
 */

@Slf4j
@RestController
public class BrowserSecurityController {

  // 从session中获取url先存储到requestCache中
  private RequestCache requestCache = new HttpSessionRequestCache();

  // 发送重定向
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Autowired
  private SecurityProperties securityProperties;

  /**
   * 当需要身份认证时跳转到这里
   */
  @RequestMapping("/authentication/require")
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    SavedRequest savedRequest = requestCache.getRequest(request, response);
    if (savedRequest != null) {
      String target = savedRequest.getRedirectUrl();
      log.info("引发跳转的url {}",target);
      // 访问资源时直接重定向到自定义登陆页面
      if (StringUtils.endsWithIgnoreCase(target, ".html")) {
        redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
      }
    }
    return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
  }
}
