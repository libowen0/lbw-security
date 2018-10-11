package com.lbw.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/10.
 */
@Data
public class UserQueryCondition {

  @ApiModelProperty(value = "用户名称",example = "xdd")
  private String username;
  @ApiModelProperty(value = "用户年龄",example = "123")
  private Integer age;
}
