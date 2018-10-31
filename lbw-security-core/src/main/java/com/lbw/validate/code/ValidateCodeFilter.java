package com.lbw.validate.code;

import com.lbw.properties.SecurityProperties;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Author by lbw , Date on 2018/10/12.
 */

// OncePerRequestFilter 保证过滤器只会被调一次
// InitializingBean 项目启动时获取urls配置参数
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

//  需要验证码配置的url
  private Set<String> urls = new HashSet<>();
  private AuthenticationFailureHandler failureHandler;

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  private SecurityProperties securityProperties;

  private AntPathMatcher pathMatcher = new AntPathMatcher();

  @Override
  public void afterPropertiesSet() throws ServletException {
    super.afterPropertiesSet();
    String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),",");

    for (String configUrl : configUrls) {
      urls.add(configUrl);
    }
    urls.add("/authentication/form");
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    boolean action = false;
    for (String url : urls){
//      判断url是否在配置项内 是的话校验请求
      if(pathMatcher.match(url,request.getRequestURI())){
        action = true;
      }
    }

    if (action) {
      try {
        validate(new ServletWebRequest(request));
      } catch (ValidateCodeException e) {
        // 如果验证出错 调用失败认证处理器
        failureHandler.onAuthenticationFailure(request, response, e);
        return;
      }
    }
    // 如果不是登陆请求直接放行
    filterChain.doFilter(request, response);
  }

  private void validate(ServletWebRequest request) throws ServletRequestBindingException {
    ImageCode codeInSession = (ImageCode) sessionStrategy
        .getAttribute(request, ValidateCodeController.SESSION_KEY);
    String codeInRequest = ServletRequestUtils
        .getStringParameter(request.getRequest(), "imageCode");

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException("验证码的值不能为空");
    }

    if (codeInSession == null) {
      throw new ValidateCodeException("验证码不存在");
    }

    if(codeInSession.isExpried()){
      sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
      throw new ValidateCodeException("验证码已过期");
    }

    if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
      throw new ValidateCodeException("验证码不匹配");
    }

    // 验证码验证成功 从session中移除验证码
    sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
  }


  public AuthenticationFailureHandler getFailureHandler() {
    return failureHandler;
  }

  public void setFailureHandler(
      AuthenticationFailureHandler failureHandler) {
    this.failureHandler = failureHandler;
  }

  public SecurityProperties getSecurityProperties() {
    return securityProperties;
  }

  public void setSecurityProperties(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }
}
