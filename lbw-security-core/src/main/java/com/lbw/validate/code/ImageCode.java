package com.lbw.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/12.
 */

@Data
public class ImageCode {

// 图片
  private BufferedImage image;
// 图片内容
  private String code;
// 验证码过期时间
  private LocalDateTime expireTime;

  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    this.image = image;
    this.code = code;
    this.expireTime = expireTime;
  }

  public ImageCode(BufferedImage image, String code, int expireIn) {
    this.image = image;
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public boolean isExpried() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
