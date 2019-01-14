package com.lbw.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import sun.applet.AppletEvent;
import sun.applet.AppletListener;

/**
 * Author by lbw , Date on 2018/10/11.
 */

// tomcat容器创建之后开始监听
@Component
@Slf4j
public class QueueListener implements AppletListener {

  @Autowired
  private MockQueue mockQueue;

  @Autowired
  private DefferredResultHolder resultHolder;

  @Override
  public void appletStateChanged(AppletEvent appletEvent) {

    new Thread(() -> {
      while (true) {
        if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
          String orderNumer = mockQueue.getCompleteOrder();
          log.info("返回订单处理结果" + orderNumer);
          resultHolder.getMap().get(orderNumer).setResult("place order success");
          mockQueue.setCompleteOrder(null);
        } else {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }
}
