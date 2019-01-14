package com.lbw.domain;

import lombok.Data;

/**
 * Author by lbw , Date on 2018/10/10.
 */

@Data
public class FileInfo {

  private String path;

  public FileInfo(String path) {
    this.path = path;
  }
}
