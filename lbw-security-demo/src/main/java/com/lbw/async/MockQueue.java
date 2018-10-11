package com.lbw.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Author by lbw , Date on 2018/10/11.
 */

@Slf4j
@Component
public class MockQueue {

  //  下单
  private String placeOrder;
  //  订单完成
  private String completeOrder;

  public String getPlaceOrder() {
    return placeOrder;
  }

  public void setPlaceOrder(String placeOrder) {
    new Thread(() -> {
      log.info("接到下单请求" + placeOrder);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      this.completeOrder = placeOrder;
      log.info("下单请求处理完毕" + placeOrder);
    }).start();
  }

  public String getCompleteOrder() {
    return completeOrder;
  }

  public void setCompleteOrder(String completeOrder) {
    this.completeOrder = completeOrder;
  }
}
