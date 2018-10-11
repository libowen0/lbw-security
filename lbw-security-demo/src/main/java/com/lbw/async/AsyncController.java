package com.lbw.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Author by lbw , Date on 2018/10/11.
 */

@Slf4j
@RestController
public class AsyncController {

  @Autowired
  MockQueue mockQueue;

  @Autowired
  DefferredResultHolder resultHolder;

//  同步处理 线程阻塞
//  @RequestMapping("/order")
//  public String order(){
//    log.info("主线程开始");
//    try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//    log.info("主线程返回");
//    return "SUCCESS";
//  }


//  异步处理 开启副线程处理具体逻辑 非阻塞
//  增加系统吞吐量，但是具体处理逻辑依赖与主线程
//  @RequestMapping("/order")
//  public Callable<String> order(){
//    log.info("主线程开始");
//
////    单开一个副线程用于处理业务
//    Callable<String> result = () -> {
//      log.info("副线程开始");
//      Thread.sleep(1000);
//      log.info("副线程返回");
//      return "SUCCESS";
//    };
//    log.info("主线程返回");
//    return result;
//  }

  @RequestMapping("/order")
  public DeferredResult<String> order(){
    log.info("主线程开始");
    String orderNumber = RandomStringUtils.randomNumeric(8);
    mockQueue.setPlaceOrder(orderNumber);

    DeferredResult<String> result = new DeferredResult<>();
    return resultHolder.getMap().put(orderNumber,result);
  }

}
