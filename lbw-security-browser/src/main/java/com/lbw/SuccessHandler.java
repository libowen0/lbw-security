package com.lbw;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbw.properties.LoginType;
import com.lbw.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Author by lbw , Date on 2018/10/12.
 * 认证成功之后进行调用，打印authentication信息
 * 不是Json形式请求，返回存储在缓存中的重定向页面
 *
 */
@Slf4j
@Component
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * 由Spring mvc 提供用于处理Json对象
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }
    }
}
