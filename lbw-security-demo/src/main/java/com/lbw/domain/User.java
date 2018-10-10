package com.lbw.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.lbw.validate.MyConstraint;
import java.util.Date;
import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/10.
 */

@Data
public class User {

  public interface UserSimpleView{};
  public interface UserDetailView extends UserSimpleView{};

  @JsonView(UserSimpleView.class)
  private Long id;

  @JsonView(UserSimpleView.class)
  @NotBlank(message = "用户名不能为空")
  private String username;


  @MyConstraint(message = "这是一个测试")
  @JsonView(UserDetailView.class)
  private Integer age;

  @JsonView(UserSimpleView.class)
  Date birthday;
}
