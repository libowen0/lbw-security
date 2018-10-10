package com.lbw.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lbw.domain.User;
import com.lbw.domain.UserQueryCondition;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/")
  @JsonView(User.UserSimpleView.class)
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

  @GetMapping("/{id:\\d+}")
  @JsonView(User.UserDetailView.class)
  public User getInfo(@PathVariable String id){
    User user = new User();
    user.setUsername("xdd1");
    return user;
  }

  @PostMapping
  public User create(@RequestBody User user){
    System.out.println(user.getId());
    System.out.println(user.getAge());
    System.out.println(user.getUsername());
    System.out.println(user.getBirthday());
    user.setId(1L);
    return user;
  }


}
