package com.lbw;

import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author by lbw , Date on 2018/10/31.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class NormalTest {


  @Autowired
  private DataSource dataSource;

  @Test
  public void test(){
    System.out.println(dataSource);
  }
}
