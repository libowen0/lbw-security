package com.lbw.controller;

import com.lbw.domain.FileInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author by lbw , Date on 2018/10/10.
 */

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

  private String floder = "D:\\lbw-security\\lbw-security-demo\\src\\main\\java\\com\\lbw\\controller";


  @PostMapping
  public FileInfo upload(MultipartFile file) throws IOException {
    System.out.println(file.getName());
    System.out.println(file.getOriginalFilename());
    System.out.println(file.getSize());

    File localFile = new File(floder, System.currentTimeMillis() + ".txt");

//    把传入文件写入本地
    file.transferTo(localFile);
    return new FileInfo(localFile.getAbsolutePath());
  }

  @GetMapping("/{id}")
  public void download(@PathVariable String id, HttpServletRequest request,
      HttpServletResponse response) {
//    浏览器下载
    try (
//        本地文件输入流
        InputStream inputStream = new FileInputStream(new File(floder, id + ".txt"));
//        浏览器响应输出流
        OutputStream outputStream = response.getOutputStream();
    ) {

//      设置内容的MIME类型
      response.setContentType("application/x-download");
//      response.setContentType("text/plain");

//      设置文件保存
      response.addHeader("Content-Disposition","attachement;filename=test.txt");
//      设置文件直接显示
//    response.addHeader("Content-Disposition","inline;filename=test.txt");
//      将输入流拷贝带输出流中
      IOUtils.copy(inputStream,outputStream);
      outputStream.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
