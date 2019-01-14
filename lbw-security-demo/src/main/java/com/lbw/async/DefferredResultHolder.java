package com.lbw.async;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Author by lbw , Date on 2018/10/11.
 */
@Data
@Component
public class DefferredResultHolder {

//  key： 订单号 value: 订单处理结果
  private Map<String,DeferredResult> map = new HashMap<>();

}
