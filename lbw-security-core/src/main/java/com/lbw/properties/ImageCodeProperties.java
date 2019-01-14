package com.lbw.properties;

import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/17.
 */

@Data
public class ImageCodeProperties {

  int width = 67;
  int height = 23;
  int length = 4;
  int expireIn = 60;
  String url;

}
