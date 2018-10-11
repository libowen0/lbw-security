package com.lbw.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lbw.domain.User;
import com.lbw.domain.UserQueryCondition;
import com.lbw.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author by lbw , Date on 2018/10/10.
 */

@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping
  @JsonView(User.UserSimpleView.class)
  @ApiOperation(value = "用户列表查询")
  public List<User> query(UserQueryCondition username,@PageableDefault(sort = "username,desc") Pageable pageable){
//  public List<User> query(@RequestParam(required = false,defaultValue = "tom") String username){

    System.out.println(pageable.getSort());
    System.out.println(pageable.getPageNumber());
    System.out.println(pageable.getPageSize());

    User user1 = new User();
    user1.setUsername("xdd1");
    user1.setAge(12);
    User user2 = new User();
    user2.setUsername("xdd2");
    user2.setAge(15);
    User user3 = new User();
    user3.setUsername("xdd3");
    user3.setAge(1);
    List<User> users = new ArrayList<>();
    users.add(user1);
    users.add(user2);
    users.add(user3);
    return users;
  }

  @GetMapping("/{id}")
  @JsonView(User.UserDetailView.class)
  public User getInfo(@ApiParam(value = "用户id") @PathVariable Integer id){

//    throw  new RuntimeException("user not exist");
    System.out.println("进入getInfo服务");
    User user = new User();
    user.setUsername("xdd1");
    return user;
  }

  @PostMapping
//  public User create(@Valid @RequestBody User user, BindingResult errors){
  public User create(@Valid @RequestBody User user){

//    if(errors.hasErrors()){
//      errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//    }

    System.out.println(user.getId());
    System.out.println(user.getAge());
    System.out.println(user.getUsername());
    System.out.println(user.getBirthday());
    user.setId(1L);
    return user;
  }

  @PutMapping
  public User update(@Valid @RequestBody User user, BindingResult errors){

    if(errors.hasErrors()){
      errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
    }

    System.out.println(user.getId());
    System.out.println(user.getAge());
    System.out.println(user.getUsername());
    System.out.println(user.getBirthday());
    user.setId(1L);
    return user;
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id){
    System.out.println(id);
  }
}
