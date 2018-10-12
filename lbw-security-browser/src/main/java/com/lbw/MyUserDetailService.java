package com.lbw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Author by lbw , Date on 2018/10/11.
 */

@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 根据用户名查找用户信息
//    log.info("登陆用户名"+ username);
//    log.info("数据库密码"+ passwordEncoder.encode("123456"));
    return new User(username,passwordEncoder.encode("123456"),
        true,true,true,true,
        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }
}
