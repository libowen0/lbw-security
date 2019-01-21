package com.lbw.properties;

import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/11.
 */

@Data
public class BrowserProperties {

  private String loginPage = "/signIn.html";

  private LoginType loginType = LoginType.JSON;

  private int rememberMeSeconds = 3600;
}
