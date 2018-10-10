package com.lbw.service;

import org.springframework.stereotype.Service;

/**
 * Author by lbw , Date on 2018/10/10.
 */
@Service
public class HelloService {

  public void greeting(String name){
    System.out.println("hello"+ name);
  }
}
