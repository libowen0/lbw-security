package com.lbw.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/10.
 */

@Data
public class User {

  public interface UserSimpleView{};
  public interface UserDetailView extends UserSimpleView{};



  @JsonView(UserSimpleView.class)
  private String username;
  @JsonView(UserDetailView.class)
  private Integer age;
}
