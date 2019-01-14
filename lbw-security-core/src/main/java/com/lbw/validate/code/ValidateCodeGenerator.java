package com.lbw.validate.code;

import javax.xml.ws.Service;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Author by lbw , Date on 2018/10/17.
 */
public interface ValidateCodeGenerator {

  ImageCode generate(ServletWebRequest request);

}
