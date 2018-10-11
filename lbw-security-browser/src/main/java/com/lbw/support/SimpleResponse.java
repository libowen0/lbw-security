package com.lbw.support;

import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/11.
 */

@Data
public class SimpleResponse {

  private Object content;

  public SimpleResponse(Object content) {
    this.content = content;
  }
}
