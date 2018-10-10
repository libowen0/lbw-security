package com.lbw.exception;

import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/10.
 */
@Data
public class UserNotExistException extends RuntimeException {

  private String id;

  public UserNotExistException(String id) {
    super("user not exist");
     this.id = id;
  }
}
